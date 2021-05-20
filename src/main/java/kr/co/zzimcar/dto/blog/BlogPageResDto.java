package kr.co.zzimcar.dto.blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BlogPageResDto {
  private int totalCnt;
  private List<BlogResDto> list;
}
