package kr.co.zzimcar.dto.book;


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
    private Date publicationAt;
    private int price;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public BookDto(BookReqDto bookReqDto) {
        this.author = bookReqDto.getAuthor();
        this.publisher = bookReqDto.getPublisher();
        this.publicationAt = bookReqDto.getPublicationAt();
        this.price = bookReqDto.getPrice();
        this.title = bookReqDto.getTitle();
    }

    public BookDto(int pid, BookReqDto bookReqDto) {
        this.pid = pid;
        this.author = bookReqDto.getAuthor();
        this.publisher = bookReqDto.getPublisher();
        this.publicationAt = bookReqDto.getPublicationAt();
        this.price = bookReqDto.getPrice();
        this.title = bookReqDto.getTitle();
    }
}
