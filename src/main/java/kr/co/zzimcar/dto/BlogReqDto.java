package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BlogReqDto {
  @NotNull(message = "제목을 입력해주세요")
  private String title;
  @NotNull(message = "내용을 입력해주세요")
  private String post;
  @NotNull(message = "작성자를 입력해주세요")
  private String writer;
}
