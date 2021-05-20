package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_NOT_FOUND("BLOG_10001", "블로그 정보를 찾을 수 없습니다."),
  BLOG_PAGING_REQ_PARAM_INVALID_SP("BLOG_10002", "sp는 0보다 큰 값이어야 합니다."),
  BLOG_PAGING_REQ_PARAM_INVALID_CNT("BLOG_10003", "cnt는 0보다 큰 값이어야 합니다."),
  BLOG_PAGING_REQ_PARAM_INVALID_SORT("BLOG_10004", "sort는 D 또는 A 여야함"),
  BLOG_RETRIVE_NOT_EXIST("BLOG_10005", "데이터가 존재하지 않습니다."),

  SQL_EXCEPTION("SQL_10000", "데이터 저장/불러오기 실패");

  private final String code;
  private final String message;
}
