package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BlogResByCntDto {
  private int totalCnt;
  private List<BlogDto> list;

  public BlogResByCntDto(int totalCnt, List<BlogDto> blogDto) {
    this.totalCnt = totalCnt;
    this.list = blogDto;
  }
}
