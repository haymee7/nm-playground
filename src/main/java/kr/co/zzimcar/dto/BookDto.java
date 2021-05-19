package kr.co.zzimcar.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class BookDto {

  private int pid;
  private String author;
  private String publisher;
  private Date publication_date;
  private int price;
  private String title;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  private LocalDateTime delete_at;

  public BookDto(BookReqDto bookReqDto){
    this.author = bookReqDto.getAuthor();
    this.publisher = bookReqDto.getPublisher();
    this.publication_date = bookReqDto.getPublication_date();
    this.price = bookReqDto.getPrice();
    this.title = bookReqDto.getTitle();
  }

  public BookDto(int pid, BookReqDto bookReqDto){
    this.pid = pid;
    this.author = bookReqDto.getAuthor();
    this.publisher = bookReqDto.getPublisher();
    this.publication_date = bookReqDto.getPublication_date();
    this.price = bookReqDto.getPrice();
    this.title = bookReqDto.getTitle();
  }
}
