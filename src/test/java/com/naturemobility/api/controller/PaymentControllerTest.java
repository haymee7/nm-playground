package com.naturemobility.api.controller;

import com.naturemobility.api.dto.billgate.BillgatePayReqDto;
import com.naturemobility.api.dto.billgate.BillgateRefReqDto;
import com.naturemobility.api.service.BillgateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest {
  @Autowired
  private BillgateService billgateService;

  @Test
  public void pay() {
    BillgatePayReqDto billgatePayReqDto = new BillgatePayReqDto();
    billgatePayReqDto.setExpireDate("2412");
    billgatePayReqDto.setItemCode("test-item");
    billgatePayReqDto.setItemName("monthly installment test item");
    billgatePayReqDto.setMemberPid(5496);
    billgatePayReqDto.setOrderNo("TEST_ORDER_NO_10011");
    billgatePayReqDto.setOrdererEmail("haymee@kakao.com");
    billgatePayReqDto.setOrdererId("zzimcar_tester");
    billgatePayReqDto.setOrdererName("테스터");
    billgatePayReqDto.setPasswd("15");
    billgatePayReqDto.setPayAmount("555000");
    billgatePayReqDto.setQuota(1);
    billgatePayReqDto.setPinNo("5365101064399342");
    billgatePayReqDto.setSaveCard(false);
    billgatePayReqDto.setSocialNo("880502");

    System.out.println(billgateService.pay(billgatePayReqDto));
  }

  @Test
  public void refund() {
    BillgateRefReqDto billgateRefReqDto = new BillgateRefReqDto();
    billgateRefReqDto.setOrderNo("TEST_ORDER_NO_10008");
    billgateRefReqDto.setRefundAmount(55000);
    billgateRefReqDto.setTransactionId("2020060913C2104596");

    System.out.println(billgateService.refund(billgateRefReqDto));
  }
}