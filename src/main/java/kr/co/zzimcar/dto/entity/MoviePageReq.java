package kr.co.zzimcar.dto.entity;

import lombok.Data;

import java.util.List;

@Data
public class MoviePageReq {

  private Long totalCnt;
  private List<MovieReq> list;
}

