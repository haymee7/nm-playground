package kr.co.zzimcar.dto.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class BookResDto {
    private String author;
    private String publisher;
    private Date publication_at;
    private int price;
    private String title;
    private LocalDateTime created_at;

    public BookResDto(BookDto bookDto) {
        this.author = bookDto.getAuthor();
        this.publisher = bookDto.getPublisher();
        this.publication_at = bookDto.getPublication_at();
        this.price = bookDto.getPrice();
        this.title = bookDto.getTitle();
        this.created_at = bookDto.getCreated_at();
    }
}
