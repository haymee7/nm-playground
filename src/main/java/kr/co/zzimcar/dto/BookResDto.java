package kr.co.zzimcar.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookResDto {
  private String author;
  private String publisher;
  private Date publication_date;
  private int price;
  private String title;

  public BookResDto(BookDto bookDto){
    this.author = bookDto.getAuthor();
    this.publisher = bookDto.getPublisher();
    this.publication_date = bookDto.getPublication_date();
    this.price = bookDto.getPrice();
    this.title = bookDto.getTitle();
  }
}
