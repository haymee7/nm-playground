package kr.co.zzimcar.dto.book;

import lombok.Data;

import java.util.List;

@Data
public class BookDataDto {
  private int totalCnt;
  private List<BookResDto> list;
}
