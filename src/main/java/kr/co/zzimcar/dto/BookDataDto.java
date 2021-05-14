package kr.co.zzimcar.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDataDto {
  private int totalCnt;
  private List<BookDto> list;

  public BookDataDto(List<BookDto> bookDto, int totalCnt){
    this.list = bookDto;
    this.totalCnt = totalCnt;
  }

}
