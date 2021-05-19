package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_RETRIEVE_FAILED("BLOG_11119", "블로그 불러오기 실패"),
  BLOG_REFRESH_FAILED("BLOG_11111", "블로그 수정 실패"),
  BLOG_INTRETRIEVE_FAILED("BLOG_11119","블로그 통합 수정 실패"),

  BOOK_UPLOAD_FAILED("BOOK_0001", "책 업로드 실패"),
  BOOK_RETRIEVEONE_FAILED("BOOK_0002","책 정보불러오기 실패"),
  BOOK_RETRIEVE_FAILED("BOOK_0003","책 정보 순서로 불러오기 실패"),
  BOOK_REVICE_FAILED("BOOK_0004","책 정보 수정 실패");



  private final String code;
  private final String message;
}
