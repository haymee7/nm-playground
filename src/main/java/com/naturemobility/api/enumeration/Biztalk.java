package com.naturemobility.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Biztalk {

    @Getter
    @AllArgsConstructor
    public enum BTN_TYPE {
        DS("배송조회"),
        WL("웹링크"),
        AL("앱링크"),
        BK("봇키워드"),
        MD("메세지전달");

        private String desc;

        public static BTN_TYPE compare(String name) {
            try {
                return valueOf(name);
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Getter
    @AllArgsConstructor
    public enum CODE {
        API_SUCCESS("1000");

        private String code;
    }

    @Getter
    @AllArgsConstructor
    public enum TMPLT_CODE {
        ZZIM_DEV_ALERT("zzim-dev-alert");

        private String code;
    }
}
