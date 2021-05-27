package kr.co.zzimcar.dto.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MovieFindReq {

  @NotNull(message = "startPoint를 입력하세요")
  @Min(value = 0, message = "0이상의 숫자를 입력하세요")
  private Integer sp;

  @NotNull(message = "cnt를 입력하세요")
  @Min(value = 1, message = "1이상이여야 합니다")
  private Integer cnt;

  @NotBlank(message = "sort를 입력해야합니다")
  private String sort;
}
