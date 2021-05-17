package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_NOT_FOUND("BLOG_10001", "블로그 정보를 찾을 수 없습니다."),
  BLOG_LIST_FAILED("BLOG_10002", "블로그 목록 불러오기 실패"),
  BOOK_SAVE_FAILED("BOOK_10000", "책 저장 실패"),
  BOOK_NOT_FOUND("BOOK_10001", "책 정보를 찾을 수 없습니다."),
  BOOK_LIST_FAILED("BOOK_10002", "책 목록 불러오기 실패");

  private final String code;
  private final String message;
}
