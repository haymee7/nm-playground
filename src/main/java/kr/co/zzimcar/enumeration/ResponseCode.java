package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_NOT_FOUND("BLOG_10001", "블로그 정보를 찾을 수 없습니다."),
  BLOG_LIST_FAILED("BLOG_10002", "블로그 목록 불러오기 실패"),
  BLOG_DELETE_FAILED("BLOG_10003", "블로그 삭제 실패"),
  BLOG_NOT_EXIST("BLOG_10004", "블로그가 이미 삭제되었거나, 잘못된 pid번호 입니다."),
  BLOG_UPDATE_FAILED("BLOG_10005", "블로그 수정 실패"),

  BOOK_SAVE_FAILED("BOOK_10000", "책 저장 실패"),
  BOOK_NOT_FOUND("BOOK_10001", "책 정보를 찾을 수 없습니다."),
  BOOK_LIST_FAILED("BOOK_10002", "책 목록 불러오기 실패"),
  BOOK_RETRIVE_NOT_EXIST("BOOK_10003", "데이터가 존재하지 않습니다."),
  BOOK_UPDATE_FAILED("BOOK_10004", "책 수정 실패"),
  BOOK_DELETE_FAILED("BOOK_10005", "책 삭제 실패"),
  BOOK_NOT_EXIST("BOOK_10006", "책이 이미 삭제되었거나, 잘못된 pid번호 입니다."),

  // TODO: 오류 코드 중복
  BLOG_PAGING_REQ_PARAM_INVALID_SP("BLOG_10002", "sp는 0이상 값이어야 합니다."),
  BLOG_PAGING_REQ_PARAM_INVALID_CNT("BLOG_10003", "cnt는 0보다 큰 값이어야 합니다."),
  BLOG_PAGING_REQ_PARAM_INVALID_SORT("BLOG_10004", "sort는 D 또는 A 여야함"),
  BLOG_RETRIVE_NOT_EXIST("BLOG_10005", "데이터가 존재하지 않습니다."),

  SQL_EXCEPTION("SQL_10000", "데이터 저장/불러오기 실패");

  private final String code;
  private final String message;
}
