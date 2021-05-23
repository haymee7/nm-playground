package kr.co.zzimcar.dto.blog;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BlogsReqDto {
    @NotNull(message = "startPoint를 입력하세요") // null이여야 valid가능(?)
    @Min(value = 0, message = "0이상의 숫자를 입력하세요")
    private Integer sp;

    @NotNull(message = "cnt를 입력하세요")
    @Min(value = 1, message = "1이상이여야 합니다") //최소값이 1이상이여야함
    private Integer cnt;
}
