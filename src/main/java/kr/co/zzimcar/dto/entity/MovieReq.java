package kr.co.zzimcar.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "movie_lhw")  //테이블 이름으로 매핑 없으면 클래스명으로 매핑
public class MovieReq {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private int pid;

  @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
  @Column(name = "director")
  private String director;

  @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
  @Column(name = "actor")
  private String actor;

  @NotNull(message = "개봉일을 입력하세요")
  @PastOrPresent(message = "날짜를 확인하세요")
  @Column(name = "release_at")
  private Date releaseAt;

  @NotNull(message = "가격을 입력하세요")
  @PositiveOrZero(message = "가격은 양수이거나 0이여야 합니다")
  @Column(name = "price")
  private Integer price;

  @NotNull(message = "제목을 입력하세요")
  @NotBlank(message = "한 글자 이상의 문자를 쓰세요")
  @Column(name = "title")
  private String title;


}
