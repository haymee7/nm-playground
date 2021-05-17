package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BookResDto {
  private int pid;
  private String writer;
  private String publisher;
  private LocalDateTime publishDate;
  private String price;
  private String title;
  private LocalDateTime createdAt;

  public BookResDto(BookDto bookDto) {
    this.pid = bookDto.getPid();
    this.writer = bookDto.getWriter();
    this.publisher = bookDto.getPublisher();
    this.publishDate = bookDto.getPublishDate();
    this.price = bookDto.getPrice();
    this.title = bookDto.getTitle();
    this.createdAt = bookDto.getCreatedAt();
  }
}
