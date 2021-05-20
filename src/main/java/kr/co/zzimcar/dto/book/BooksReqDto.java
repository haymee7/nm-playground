package kr.co.zzimcar.dto.book;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BooksReqDto {
  @NotNull(message = "startPoint를 입력하세요")
  private int sp;

  @NotNull(message = "cnt를 입력하세요")
  private int cnt;

  @NotNull(message = "order를 입력하세요")
  private String order;
}