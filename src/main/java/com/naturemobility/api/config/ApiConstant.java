package com.naturemobility.api.config;

/**
 * API 서비스에서 전역으로 사용하는 상수 모음
 */
public class ApiConstant {
    /**
     * 권한 관련 상수
     */
    public static final class ROLE {
        public static final String PREFIX = "ROLE_";
        public static final String ZZIMCAR = "ZZIMCAR";
        public static final String CARSHARING = "CARSHARING";
        public static final String TAXI = "TAXI";
        public static final String PERSONAL = "PERSONAL";
        public static final String DIRECT = "DIRECT";
        public static final String NM_USER = "NM_USER";
        public static final String ADMIN = "ADMIN";
    }

    /**
     * 알 수 없는 에러 외부 유출 텍스트
     * 오류 위치 파악을 위해 1회 이상 사용하지 말 것
     */
    public static final class ERROR {
        public static final String MEMBER_CONTROLLER_GET_MEMBER_1 = "GET MEMBER ERROR[1]";
        public static final String MEMBER_CONTROLLER_HASID_1 = "MEMBER EXIST ERROR[1]";
        public static final String MEMBER_CONTROLLER_HASEMAIL_1 = "MEMBER EXIST EMAIL ERROR[1]";

        public static final String MESSAGE_CONTROLLER_SMSVERIFY_1 = "SMS VERIFY ERROR[1]";

        public static final String KAKAO_SERVICE_REQUEST_TOKEN_1 = "KAKAO GET TOKEN ERROR[1]";
        public static final String KAKAO_SERVICE_GETKAKAOPROFILE_1 = "KAKAO GET PROFILE ERROR[1]";
        public static final String KAKAO_SERVICE_GETKAKAOPROFILE_2 = "KAKAO GET PROFILE ERROR[2]";

        public static final String CLIENT_SERVICE_GENERATETOKEN_1 = "GENERATE TOKEN ERROR[1]";

        public static final String POINT_SERVICE_GETHISTORY_1 = "GET POINT HISTORY ERROR[1]";
    }

    /**
     * Npro 서비스에서 사용하는 상수
     */
    public static final class NPRO {
        public static final class MSG_TYPE {
            public static final int SMS = 4;
            public static final int URL = 5;
            public static final int MMS = 6;
            public static final int BARCODE = 7;
        }
    }

    /**
     * 토큰 타입
     */
    public static final class GRANT {
        public static final String ACCESS = "access_token";
        public static final String REFRESH = "refresh_token";
    }

    /**
     * 소셜 타입
     */
    public static final class SOCIAL {
        public static final String KAKAO = "KAKAO";
        public static final String GOOGLE = "GOOGLE";
        public static final String NAVER = "NAVER";
        public static final String FACEBOOK = "FB";
    }

    /**
     * 상태값 상수 모음
     */
    public static final class STATUS {
        public static final class LICENSE {
            public static final String ENTER = "ENTER";                             // 면허검증 요청 받음
            public static final String REQUEST_DLV_TOKEN = "REQUEST_DLV_TOKEN";     // 도로교통공단에 토큰 요청
            public static final String GET_DLV_TOKEN = "GET_DLV_TOKEN";             // 도로교통공단 토큰 획득
            public static final String ENCODING_BODY = "ENCODING_BODY";             // 도로교통공단에 보낼 전문 암호화
            public static final String SEND_TO_DLV = "SEND_TO_DLV";                 // 도로교통공단에 요청 보냄
            public static final String DLV_RETURN_SUCCESS = "DLV_RETURN_SUCCESS";   // 도로교통공단으로부터 응답 받음
            public static final String DLV_RETURN_FAIL = "DLV_RETURN_FAIL";         // 도로교통공단으로부터 응답 받지 못함
        }
    }

    /**
     * 기타 타입
     */
    public static final class TYPE {
        /**
         * 계정 찾기 타입
         */
        public static final class FIND {
            public static final String ID = "ID";
            public static final String PASSWORD = "PASSWORD";
        }
    }

    /**
     * 포인트
     */
    public static final class POINT {
        public static final int WELCOME = 3000;
    }

}
