package kr.co.zzimcar.dto.book;



import lombok.Data;


import javax.validation.constraints.*;
import java.util.Date;

@Data
public class BookReqDto {


    private int pid;

    @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
    private String author;

    @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
    private String publisher;

    @NotNull(message = "출판일을 입력하세요")
    @PastOrPresent(message = "날짜를 확인하세요")   // 중복 가능, @PastOrPresent 입력 날짜가  현재 또는 과거이여야만 패스
    private Date publicationAt;


    @NotNull(message = "가격을 입력하세요")            // @Null 모든 타입 가능
    @PositiveOrZero(message = "가격은 양수이거나 0이여야 합니다")  // 양수이거나 0 이여야만 패스스  private int price;
    private Integer price;

    @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
    private String title;
}

