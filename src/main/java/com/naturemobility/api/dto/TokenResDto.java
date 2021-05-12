package com.naturemobility.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "클라이언트 토큰 응답 모델")
public class TokenResDto {

    private String accessToken;
    private String refreshToken;
}
