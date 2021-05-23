package kr.co.zzimcar.dto.blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BlogReqDto {
    @NotNull(message = "제목을 입력해주세요")
    @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
    private String title;

    @NotNull(message = "내용을 입력해주세요")
    @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
    private String post;

    @NotNull(message = "작성자를 입력해주세요")
    @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
    private String writer;
}
