package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookResByCntDto {

  private int totalCnt;
  private List<BookDto> list;

  public BookResByCntDto(int totalCnt, List<BookDto> list) {
    this.totalCnt = totalCnt;
    this.list = list;
  }
}
