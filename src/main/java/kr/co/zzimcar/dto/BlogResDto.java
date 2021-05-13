package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BlogResDto {
  private int pid;
  private String post;
  private String writer;
  private String title;
  private LocalDateTime createdAt;

  public BlogResDto(BlogDto blogDto) {
    this.pid = blogDto.getPid();
    this.post = blogDto.getPost();
    this.writer = blogDto.getWriter();
    this.title = blogDto.getTitle();
    this.createdAt = blogDto.getCreatedAt();
  }
}
