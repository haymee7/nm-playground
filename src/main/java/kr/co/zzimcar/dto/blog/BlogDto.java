package kr.co.zzimcar.dto.blog;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BlogDto {
  private int pid;
  private String title;
  private String post;
  private String writer;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public BlogDto(BlogReqDto blogReqDto) {
    this.title = blogReqDto.getTitle();
    this.post = blogReqDto.getPost();
    this.writer = blogReqDto.getWriter();
  }

}
