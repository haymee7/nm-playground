package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
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

  public BlogDto(int pid, BlogReqDto blogReqDto){
    this.pid = pid;
    this.title = blogReqDto.getTitle();
    this.post = blogReqDto.getPost();
    this.writer = blogReqDto.getWriter();

  }
}
