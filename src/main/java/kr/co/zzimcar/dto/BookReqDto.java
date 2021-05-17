package kr.co.zzimcar.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BookReqDto {
  @NotNull(message = "writer를 입력해주세요")
  @ApiModelProperty(value = "책 작가")
  private String writer;
  @NotNull(message = "publisher를 입력해주세요")
  @ApiModelProperty(value = "책 출판사")
  private String publisher;
  @NotNull(message = "publishDate를 입력해주세요")
  @ApiModelProperty(value = "출판 일자")
  private LocalDateTime publishDate;
  @NotNull(message = "price를 입력해주세요")
  @ApiModelProperty(value = "책 가격")
  private String price;
  @NotNull(message = "title를 입력해주세요")
  @ApiModelProperty(value = "책 제목")
  private String title;
}
