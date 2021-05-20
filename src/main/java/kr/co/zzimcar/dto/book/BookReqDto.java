package kr.co.zzimcar.dto.book;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
public class BookReqDto {
    @NotNull(message = "저자를 입력하세요")
    private String author;
    @NotNull(message = "출판사를 입력하세요")
    private String publisher;
    @NotNull(message = "출판일을 입력하세요")
    @PastOrPresent(message = "날짜를 확인하세요")   // 중복 가능, @PastOrPresent 입력 날짜가  현재 또는 과거이여야만 패스
    private Date publication_at;
    @NotNull(message = "가격을 입력하세요")            // @Null 모든 타입 가능
    @PositiveOrZero(message = "가격을 확인하세요")  // 양수이거나 0 이여야만 패스스  private int price;
    private int price;
    @NotNull(message = "제목을 입력하세요")
    private String title;
}

