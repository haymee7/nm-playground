package kr.co.zzimcar.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BlogReqDto {
  @NotNull(message = "title을 입력해주세요")
  @ApiModelProperty(value = "블로그 제목")
  private String title;
  @NotNull(message = "post를 입력해주세요")
  @ApiModelProperty(value = "블로그 내용")
  private String post;
  @NotNull(message = "writer를 입력해 주세요")
  @ApiModelProperty(value = "블로그 작성자")
  private String writer;
}
