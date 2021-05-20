package kr.co.zzimcar.dto.book;


import kr.co.zzimcar.exception.ApiException;
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
  private Date publication_at;
  private int price;
  private String title;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  private LocalDateTime deleted_at;

  public BookDto(BookReqDto bookReqDto){
    this.author = bookReqDto.getAuthor();
    this.publisher = bookReqDto.getPublisher();
    this.publication_at = bookReqDto.getPublication_at();
    this.price = bookReqDto.getPrice();
    this.title = bookReqDto.getTitle();
  }

  public BookDto(int pid, BookReqDto bookReqDto){
    this.pid = pid;
    this.author = bookReqDto.getAuthor();
    this.publisher = bookReqDto.getPublisher();
    this.publication_at = bookReqDto.getPublication_at();
    this.price = bookReqDto.getPrice();
    this.title = bookReqDto.getTitle();
  }
}
