package kr.co.zzimcar.dto.book;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookDto {
  private int pid;
  private String writer;
  private String publisher;
  private LocalDateTime publishAt;
  private int price;
  private String title;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public BookDto(BookReqDto bookReqDto) {
    this.writer = bookReqDto.getWriter();
    this.publisher = bookReqDto.getPublisher();
    this.publishAt = bookReqDto.getPublishAt();
    this.price = bookReqDto.getPrice();
    this.title = bookReqDto.getTitle();
  }
}
