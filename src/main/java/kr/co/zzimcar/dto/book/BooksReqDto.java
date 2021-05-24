package kr.co.zzimcar.dto.book;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class BooksReqDto {
  @NotNull(message = "startPoint를 입력하세요")
  @Min(value = 0, message = "0이상의 숫자를 입력하세요")
  private Integer sp;

  @NotNull(message = "cnt를 입력하세요")
  @Min(value = 1, message = "1이상이여야 합니다")
  private Integer cnt;

  @NotNull(message = "order를 입력하세요")
  @NotBlank(message = "DESC나 ASC를 입력해야합니다")
  private String order;

}

