package kr.co.zzimcar.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookResByCntDto {

  private int totalCnt;
  private List<BookResDto> list;
}
