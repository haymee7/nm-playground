package com.naturemobility.api;

import com.naturemobility.api.config.ApiUserDetails;
import com.naturemobility.api.controller.ObdController;
import com.naturemobility.api.controller.TestController;
import com.naturemobility.api.dto.ClientDto;
import com.naturemobility.api.dto.jastec.*;
import com.naturemobility.api.util.JastecApiUtil;
import com.naturemobility.api.util.WebClientUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

  @Autowired
  private ObdController obdController;

  @Autowired
  private JastecApiUtil jastecApiUtil;

  @Before
  public void setup() {
    ApiUserDetails userDetails = new ApiUserDetails(new ClientDto("test", "0269292401"), null);
    Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null);
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  @Test
  public void test() {
    TestController testController = new TestController();
    testController.jastec();
  }

  @Test
  public void location() {
    String deviceKey = "VONXC00009188";
    JastecDeviceResDto deviceResDto = JastecApiUtil.getDevice(deviceKey);

    String OBD_CHECKER_URL = "https://xthing.viewcar.co.kr/api/plugins/telemetry/DEVICE";

    // API 요청 URL 생성
    long startTimeSecond = System.currentTimeMillis() / 1000L;
    long endTimeSecond = startTimeSecond + 1000L;               // 1576584911000

    startTimeSecond = 1583127851000L;
    endTimeSecond = 1583217770000L;
    System.out.println("-- startTimeSecond: " + startTimeSecond);
    System.out.println("-- endTimeSecond: " + endTimeSecond);
    String jastecCallUrl = OBD_CHECKER_URL + "/" + deviceResDto.getId().getId() + "/values/timeseries" +
      "?keys=lat,lon,vehicle_speed|sensor|2|accm|7|stastics|135696,calced_sample_fuel_coms|metric|44|accm|7|stastics|2888464" +
      "&startTs=" + startTimeSecond + "&endTs=" + endTimeSecond;

    // API 요청 헤더 생성
    Map<String, String> headers = new HashMap<>();
    headers.put("X-Authorization", "Bearer " + JastecApiUtil.requestToken());

    JastecValueResDto valueResDto = WebClientUtil.WebGet(jastecCallUrl, headers, null, JastecValueResDto.class);

    System.out.println(valueResDto.getLat());

  }

  @Test
  public void location2() {
    String deviceKey = "VONXC00009188";
    long startTimeSecond = 1583127851000L;
    long endTimeSecond = 1583217770000L;

    System.out.println(JastecApiUtil.where(deviceKey, startTimeSecond, endTimeSecond));
  }
}