package kr.co.zzimcar.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogResDto {
    private int pid;
    private String post;
    private String writer;
    private String title;
    private LocalDateTime createdAt;

    public BlogResDto(BlogDto blogDto) {
        this.post = blogDto.getPost();
        this.writer = blogDto.getWriter();
        this.title = blogDto.getTitle();
        this.createdAt = blogDto.getCreatedAt();
    }


}
