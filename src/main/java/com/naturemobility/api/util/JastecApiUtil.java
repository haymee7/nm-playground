package com.naturemobility.api.util;

import com.naturemobility.api.dto.ApiResultDto;
import com.naturemobility.api.dto.jastec.*;
import com.naturemobility.api.dto.obd.ObdDetailDto;
import com.naturemobility.api.dto.obd.ObdDto;
import com.naturemobility.api.dto.obd.ObdLocationDto;
import com.naturemobility.api.dto.obd.ObdResDto;
import com.naturemobility.api.enumeration.ApiResponseCode;
import com.naturemobility.api.enumeration.Obd;
import com.naturemobility.api.exception.IntApiCustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JastecApiUtil {
  private static final Logger log = LogManager.getLogger(JastecApiUtil.class);

  private static final String TOKEN_REQUEST_URL = "https://xthing.viewcar.co.kr/api/auth/login";
  private static final String DEVICE_REQUEST_URL = "https://xthing.viewcar.co.kr/api/tenant/devices";
  private static final String COMMAND_REQUEST_URL = "https://xthing.viewcar.co.kr/api/plugins/rpc/twoway";
  private static final String OBD_CHECKER_URL = "https://xthing.viewcar.co.kr/api/plugins/telemetry/DEVICE";

  private static final String KEY_FOR_WHERE = "lat,lon";

  private static final String METHOD_USER_MSG_FROM_SERVER = "user_msg_from_server";
  private static final int DEFAULT_TIMEOUT = 30000;
  private static final int DEFAULT_REQUEST_CHECK_TIMEOUT_CNT = 13;

  private static final String PAYLOAD_DOOR_OPEN = "DOOR_OPEN";
  private static final String PAYLOAD_DOOR_CLOSE = "DOOR_CLOSE";
  private static final String PAYLOAD_HORN = "HORN";

  private static final String JASTEC_USER_NAME = "naturemobility@naturemobility.com";
  private static final String JASTEC_PASSWD = "naturemobility";

  public static String requestToken() {
    Map<String, String> headers = new HashMap<>();
    JastecLoginReqDto loginReqDto = new JastecLoginReqDto();
    loginReqDto.setUsername(JASTEC_USER_NAME);
    loginReqDto.setPassword(JASTEC_PASSWD);

    try {
      JastecLoginResDto loginResDto = WebClientUtil.WebPost(TOKEN_REQUEST_URL, headers, loginReqDto, JastecLoginResDto.class);
      if (loginResDto.getToken() == null) throw new IntApiCustomException(ApiResponseCode.JASTEC_UTIL_CANNOT_GET_TOKEN_1);

      return loginResDto.getToken();
    } catch (Exception e) {
      throw new IntApiCustomException(ApiResponseCode.JASTEC_UTIL_CANNOT_GET_TOKEN);
    }
  }

  public static JastecDeviceResDto getDevice(String deviceKey) {
    Map<String, String> headers = new HashMap<>();
    headers.put("X-Authorization", "Bearer " + requestToken());

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("deviceName", deviceKey);

    try {
      JastecDeviceResDto deviceResDto = WebClientUtil.WebGet(DEVICE_REQUEST_URL, headers, params, JastecDeviceResDto.class);
      if (deviceResDto.getId() == null) throw new IntApiCustomException(ApiResponseCode.JASTEC_UTIL_CANNOT_GET_DEVICE);

      return deviceResDto;
    } catch (Exception e) {
      throw new IntApiCustomException(ApiResponseCode.JASTEC_UTIL_CANNOT_GET_DEVICE_1);
    }
  }

  public static ApiResultDto openDoor(ObdDto obdDto) {
    return requestCommand(obdDto, PAYLOAD_DOOR_OPEN);
  }

  public static ApiResultDto closeDoor(ObdDto obdDto) {
    return requestCommand(obdDto, PAYLOAD_DOOR_CLOSE);
  }

  public static ApiResultDto horn(ObdDto obdDto) {
    return requestCommand(obdDto, PAYLOAD_HORN);
  }

  public static ApiResultDto attribute(String deviceKey) {
    try {
      // 기기정보 가져오기
      JastecDeviceResDto deviceResDto = getDevice(deviceKey);
      String attributes = String.join(",", Obd.JASTEC_ATTRIBUTE.VER.getKey(), Obd.JASTEC_ATTRIBUTE.FUEL_LEVEL.getKey(),
        Obd.JASTEC_ATTRIBUTE.MILEAGE.getKey(), Obd.JASTEC_ATTRIBUTE.LAT.getKey(), Obd.JASTEC_ATTRIBUTE.LON.getKey());

      String whereRequestUrl = new StringBuilder(OBD_CHECKER_URL)
        .append("/")
        .append(deviceResDto.getId().getId())
        .append("/values/attributes?keys=")
        .append(attributes)
        .toString();

      // API 요청 헤더 생성
      Map<String, String> headers = new HashMap<>();
      headers.put("X-Authorization", "Bearer " + requestToken());

      JastecAttributeResDto[] jastecAttributeResWrapper = WebClientUtil.WebGet(whereRequestUrl, headers, null, JastecAttributeResDto[].class);

      ApiResultDto resultDto = new ApiResultDto(true);
      resultDto.setData(new ObdDetailDto(jastecAttributeResWrapper));
      return resultDto;

    } catch (Exception e) {
      log.info("attribute::", e);
      return new ApiResultDto(ApiResponseCode.JASTEC_UTIL_FAIL_ATTRIBUTE);
    }
  }

  public static ObdResDto where(String deviceKey, long sTimeSecond, long eTimeSecond) {
    try {
      // 기기정보 가져오기
      JastecDeviceResDto deviceResDto = getDevice(deviceKey);

      String whereRequestUrl = new StringBuilder(OBD_CHECKER_URL)
        .append("/")
        .append(deviceResDto.getId().getId())
        .append("/values/timeseries?keys=")
        .append(KEY_FOR_WHERE)
        .append("&startTs=")
        .append(sTimeSecond)
        .append("&endTs=")
        .append(eTimeSecond)
        .toString();

      // API 요청 헤더 생성
      Map<String, String> headers = new HashMap<>();
      headers.put("X-Authorization", "Bearer " + requestToken());

      JastecValueResDto valueResDto = WebClientUtil.WebGet(whereRequestUrl, headers, null, JastecValueResDto.class);

      if (valueResDto.getLat().size() > 0 && valueResDto.getLat().size() > 0) {
        return new ObdResDto(true, new ObdDetailDto(convertLatLong(valueResDto.getLat(), valueResDto.getLon())));
      } else {
        return new ObdResDto(false, ApiResponseCode.JASTEC_NO_LOCATION.code());
      }
    } catch (Exception e) {
      return new ObdResDto(false, ApiResponseCode.JASTEC_UTIL_CANNOT_GET_WHERE.code());
    }
  }

  private static List<ObdLocationDto> convertLatLong(List<JastecValue> latitude, List<JastecValue> longitude) {
    List<ObdLocationDto> locations = new ArrayList<>();

    int length = latitude.size();
    for (int i = 0; i < length; i++) {
      if (latitude.get(i).getTs() == longitude.get(i).getTs()) {
        locations.add(new ObdLocationDto(latitude.get(i).getTs(), latitude.get(i).getValue(), longitude.get(i).getValue()));
      }
    }

    return locations;
  }

  private static ApiResultDto requestCommand(ObdDto obdDto, String payloadDoorClose) {
    try {
      // 기기정보 가져오기
      JastecDeviceResDto deviceResDto = getDevice(obdDto.getDeviceKey());

      // API 요청 URL 생성
      String jastecCallUrl = COMMAND_REQUEST_URL + "/" + deviceResDto.getId().getId();

      // API 요청 헤더 생성
      Map<String, String> headers = new HashMap<>();
      headers.put("X-Authorization", "Bearer " + requestToken());

      // API 요청 바디 생성
      JastecRpcCallReqDto rpcCallReqDto = new JastecRpcCallReqDto(METHOD_USER_MSG_FROM_SERVER, DEFAULT_TIMEOUT);

      long timeId = System.currentTimeMillis() / 1000L;
      JastecRpcCallParam rpcCallParam = new JastecRpcCallParam(timeId, payloadDoorClose, obdDto.getNetworkType());
      rpcCallReqDto.setParams(rpcCallParam);

      // API 요청
      JastecRpcCallResDto rpcCallResDto = WebClientUtil.WebPost(jastecCallUrl, headers, rpcCallReqDto, JastecRpcCallResDto.class);

      // 결과 수신
      if (obdResponseChecker(headers, deviceResDto.getId().getId(), rpcCallParam.getId())) {
        return new ApiResultDto(true);
      } else {
        return new ApiResultDto(ApiResponseCode.JASTEC_UTIL_FAIL_COMMAND);
      }
    } catch (Exception e) {
      log.info("requestCommand::", e);
      return new ApiResultDto(ApiResponseCode.JASTEC_UTIL_UNEXPECTED_FAIL_COMMAND);
    }
  }

  public static boolean obdResponseChecker(Map<String, String> headers, String deviceId, long timeId) {
    boolean obdResponseOk = false;
    String jastecTelemetryCheckerUrl = new StringBuilder(OBD_CHECKER_URL)
      .append("/").append(deviceId)
      .append("/values/timeseries?keys=tcpResponse,tcpResponseMqtt,tcpErrResponse")
      .append("&startTs=")
      .append(timeId * 1000 - 1)
      .append("&endTs=")
      .append(timeId * 1000 + 1)
      .toString();

    int timeOutIdx = 0;
    while (!obdResponseOk) {
      if (timeOutIdx > DEFAULT_REQUEST_CHECK_TIMEOUT_CNT) {
        break;
      } else {
        try {
          JastecTelemetryResBody telemetryResBody = WebClientUtil.WebGet(jastecTelemetryCheckerUrl, headers, null, JastecTelemetryResBody.class);
          List<JastecTcpResponse> resBodyList = telemetryResBody.getTcpResponse();
          List<JastecTcpResponse> mqttResBodyList = telemetryResBody.getTcpResponseMqtt();

          log.info("JastecTelemetryResBody[" + telemetryResBody + "]");

          if ((resBodyList != null && resBodyList.get(0).getTs() > 0 && resBodyList.get(0).getTs() == timeId * 1000)
            || (mqttResBodyList != null && mqttResBodyList.get(0).getTs() > 0 && mqttResBodyList.get(0).getTs() == timeId * 1000)) {
            obdResponseOk = true;
          } else {
            Thread.sleep(1000);
            timeOutIdx++;
          }
        } catch (Exception e) {
          return false;
        }
      }
    }

    return obdResponseOk;
  }
}
