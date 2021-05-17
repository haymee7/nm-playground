package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookDto {
  private int pid;
  private String writer;
  private String publisher;
  private LocalDateTime publishDate;
  private String price;
  private String title;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public BookDto(BookReqDto bookReqDto) {
    this.writer = bookReqDto.getWriter();
    this.publisher = bookReqDto.getPublisher();
    this.publishDate = bookReqDto.getPublishDate();
    this.price = bookReqDto.getPrice();
    this.title = bookReqDto.getTitle();
  }
}
