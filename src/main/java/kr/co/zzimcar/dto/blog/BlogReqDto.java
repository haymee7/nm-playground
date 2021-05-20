package kr.co.zzimcar.dto.blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BlogReqDto {
  @NotNull(message = "title을 입력해주세요")
  private String title;
  private String post;
  private String writer;
}
