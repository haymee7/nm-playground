package com.naturemobility.api.util;

import com.naturemobility.api.config.ApiUserDetails;
import com.naturemobility.api.dto.ClientDto;
import com.naturemobility.api.dto.biztalk.BiztalkSendReqDto;
import com.naturemobility.api.enumeration.ApiResponseCode;
import com.naturemobility.api.enumeration.Biztalk;
import com.naturemobility.api.exception.IntApiCustomException;
import com.naturemobility.api.service.BiztalkSendService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommonUtil {

  private final static Logger log = LogManager.getLogger(CommonUtil.class);

  @Autowired
  private BiztalkSendService biztalkSendServiceBean;
  public static BiztalkSendService biztalkSendService;

  @PostConstruct
  public void initialize() {
    this.biztalkSendService = biztalkSendServiceBean;
  }

  public static ClientDto getClient() {
    try {
      ApiUserDetails client = (ApiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (client == null) {
        throw new IntApiCustomException(ApiResponseCode.COMMON_NO_SERVICE);
      } else {
        return client.getClientDto();
      }

    } catch (Exception e) {
      log.debug("getService", e);
      throw new IntApiCustomException(ApiResponseCode.COMMON_NO_SERVICE_1);
    }
  }

  public static String getServiceGroup() {
    return getClient().getServiceGroup();
  }

  public static String getService() {
    return getClient().getService();
  }

  public static String getPgServiceId() {
    return getClient().getPgServiceId();
  }

  public static boolean isAdminService() {
    return getService().toUpperCase().contains("ADMIN");
  }

  public static String getClientName() {
    return getClient().getService();
  }

  public static String getServiceActiveUserRedisKey() {
    return "ga-" + getClientName().toLowerCase() + "-active-user-cnt";
  }

  public static void sendError(String service, String errorTxt) {
    try {
      errorTxt = new StringBuilder("[Client]")
        .append(getClientName())
        .append("\n")
        .append(errorTxt).toString();

      Map<String, String> replacement = new HashMap<>();
      replacement.put("project", "INT-API");
      replacement.put("service", service);
      replacement.put("errorTxt", errorTxt);

      BiztalkSendReqDto sendReqDto = new BiztalkSendReqDto();
      sendReqDto.setCountryCode("82");
      sendReqDto.setRecipient("01028250446");
      sendReqDto.setTmpltCode(Biztalk.TMPLT_CODE.ZZIM_DEV_ALERT.getCode());
      sendReqDto.setReplacement(replacement);

      biztalkSendService.send(sendReqDto);
    } catch (Exception e) {
      log.debug("카카오 에러 알림 오류", e);
    }
  }
}
