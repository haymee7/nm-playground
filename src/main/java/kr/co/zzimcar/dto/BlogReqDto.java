package kr.co.zzimcar.dto;

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
  @NotNull(message = "post를 입력해주세요")
  private String post;
  @NotNull(message = "writer를 입력해 주세요")
  private String writer;
}
