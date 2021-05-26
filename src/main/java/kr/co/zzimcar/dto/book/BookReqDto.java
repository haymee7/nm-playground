package kr.co.zzimcar.dto.book;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BookReqDto {
  // TODO: 라인 분리
  @NotNull(message = "writer를 입력해주세요")
  @ApiModelProperty(value = "책 작가")
  @Size(min = 1, max = 20)
  private String writer;

  @NotNull(message = "publisher를 입력해주세요")
  @ApiModelProperty(value = "책 출판사")
  @Size(min = 1, max = 20)
  private String publisher;

  @NotNull(message = "publish_at을 입력해주세요")
  @ApiModelProperty(value = "출판 일자")
  private LocalDateTime publishAt;

  @NotNull(message = "price를 입력해주세요")
  @ApiModelProperty(value = "책 가격")
  @Max(value = 1000000)
  @Min(value = 0)
  private int price;

  @NotNull(message = "title를 입력해주세요")
  @ApiModelProperty(value = "책 제목")
  @Size(min = 1, max = 20)
  private String title;
}
