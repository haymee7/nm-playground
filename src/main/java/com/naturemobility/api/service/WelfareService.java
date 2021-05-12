package com.naturemobility.api.service;

import com.naturemobility.api.dto.welfare.WelfarePayReqDto;
import com.naturemobility.api.dto.welfare.WelfarePointReqDto;
import com.naturemobility.api.dto.welfare.WelfareRefundReqDto;
import org.springframework.http.ResponseEntity;

public interface WelfareService {
    public abstract ResponseEntity<?> viewPoint(WelfarePointReqDto welfarePointnReqDto);
    public abstract ResponseEntity<?> payPoint(int affiliatePid, WelfarePayReqDto payReqDto);
    public abstract ResponseEntity<?> refund(int affiliatePid, WelfareRefundReqDto refundReqDto);
    public abstract int getPoint(WelfarePointReqDto welfarePointReqDto);
}
