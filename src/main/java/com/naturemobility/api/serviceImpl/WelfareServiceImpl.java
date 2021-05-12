package com.naturemobility.api.serviceImpl;

import com.naturemobility.api.dao.AffiliateDao;
import com.naturemobility.api.dto.*;
import com.naturemobility.api.dto.affiliate.AffiliateDto;
import com.naturemobility.api.dto.welfare.WelfarePayReqDto;
import com.naturemobility.api.dto.welfare.WelfarePointReqDto;
import com.naturemobility.api.dto.welfare.WelfareRefundReqDto;
import com.naturemobility.api.enumeration.ApiResponseCode;
import com.naturemobility.api.enumeration.Affiliate;
import com.naturemobility.api.exception.IntApiCustomException;
import com.naturemobility.api.service.ResponseService;
import com.naturemobility.api.service.WelfareHistoryService;
import com.naturemobility.api.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    private AffiliateDao affiliateDao;

    @Autowired
    private WelfareService kcpWelfareServiceImpl;

    @Autowired
    private WelfareService welmarketWelfareServiceImpl;

    @Autowired
    private WelfareHistoryService welfareHistoryService;

    @Override
    public int getPoint(WelfarePointReqDto welfarePointReqDto) {
        return 0;
    }

    @Override
    public ResponseEntity<?> viewPoint(WelfarePointReqDto welfarePointnReqDto) {
        AffiliateDto affiliateDto = affiliateDao.findOne(welfarePointnReqDto.getAffiliatePid());

        // 제휴업체 검사
        validationAffiliate(affiliateDto);
        ApiResultDto resultDto = new ApiResultDto();
        int point;

        // 기록
        welfareHistoryService.writeRequestView(welfarePointnReqDto);

        switch (Affiliate.compare(affiliateDto.getUrn())) {
            case BENEPIA:
                point = kcpWelfareServiceImpl.getPoint(welfarePointnReqDto);
                break;
            case WELMARKET:
                point = welmarketWelfareServiceImpl.getPoint(welfarePointnReqDto);
                break;
            default:
                throw new IntApiCustomException(ApiResponseCode.WELFARE_NOT_FOUND_AFFILIATE);
        }

        resultDto.setData(point);
        resultDto.setStatus(HttpStatus.OK);
        return ResponseService.getResult(resultDto);
    }

    private void validationAffiliate(AffiliateDto affiliateDto) {
        if (!affiliateDto.isUseWelfarePoint()) {
            throw new IntApiCustomException(ApiResponseCode.WELFARE_UNVALID_AFFILIATE);
        }
    }

    @Override
    public ResponseEntity<?> payPoint(int affiliatePid, WelfarePayReqDto payReqDto) {
        AffiliateDto affiliateDto = affiliateDao.findOne(affiliatePid);

        // 제휴업체 검사
        validationAffiliate(affiliateDto);
        payReqDto.setAffiliatePid(affiliatePid);
        ApiResultDto resultDto = new ApiResultDto();

        // 기록
        welfareHistoryService.writeRequestPay(affiliatePid, payReqDto);

        switch (Affiliate.compare(affiliateDto.getUrn())) {
            case BENEPIA:
                return kcpWelfareServiceImpl.payPoint(affiliatePid, payReqDto);
            case WELMARKET:
                return welmarketWelfareServiceImpl.payPoint(affiliatePid, payReqDto);
            default:
                resultDto.setStatus(HttpStatus.BAD_REQUEST);
                resultDto.setMessage("제휴사 정보를 찾을 수 없습니다.");
                break;
        }

        return ResponseService.getResult(resultDto);
    }

    @Override
    public ResponseEntity<?> refund(int affiliatePid, WelfareRefundReqDto refundReqDto) {
        AffiliateDto affiliateDto = affiliateDao.findOne(affiliatePid);

        // 제휴업체 검사
        validationAffiliate(affiliateDto);
        ApiResultDto resultDto = new ApiResultDto();

        // 기록
        welfareHistoryService.writeRequestRefund(affiliatePid, refundReqDto);

        switch (Affiliate.compare(affiliateDto.getUrn())) {
            case BENEPIA:
                return kcpWelfareServiceImpl.refund(affiliatePid, refundReqDto);
            case WELMARKET:
                return welmarketWelfareServiceImpl.refund(affiliatePid, refundReqDto);
            default:
                resultDto.setStatus(HttpStatus.BAD_REQUEST);
                resultDto.setMessage("제휴사 정보를 찾을 수 없습니다.");
                break;
        }

        return ResponseService.getResult(resultDto);
    }
}
