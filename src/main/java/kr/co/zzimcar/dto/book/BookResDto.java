package kr.co.zzimcar.dto.book;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data

public class BookResDto {

  private String author;
  private String publisher;
  private Date publicationAt;
  private int price;
  private String title;
  private LocalDateTime createdAt;

  public BookResDto(BookDto bookDto) {
    this.author = bookDto.getAuthor();
    this.publisher = bookDto.getPublisher();
    this.publicationAt = bookDto.getPublicationAt();
    this.price = bookDto.getPrice();
    this.title = bookDto.getTitle();
    this.createdAt = bookDto.getCreatedAt();
  }
}
