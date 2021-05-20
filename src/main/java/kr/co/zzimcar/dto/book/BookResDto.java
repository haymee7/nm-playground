package kr.co.zzimcar.dto.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class BookResDto {
  @NotNull(message = "author를 입력하세요")
  private String author;
  @NotNull(message = "publisher를 입력하세요")
  private String publisher;
  @NotNull(message = "publication_at를 입력하세요")
  private Date publication_at;
  @NotNull(message = "price를 입력하세요")
  private int price;
  @NotNull(message = "title를 입력하세요")
  private String title;
  @NotNull(message = "created_at를 입력하세요")
  private LocalDateTime created_at;

  public BookResDto(BookDto bookDto){
    this.author = bookDto.getAuthor();
    this.publisher = bookDto.getPublisher();
    this.publication_at = bookDto.getPublication_at();
    this.price = bookDto.getPrice();
    this.title = bookDto.getTitle();
    this.created_at = bookDto.getCreated_at();
  }
}
