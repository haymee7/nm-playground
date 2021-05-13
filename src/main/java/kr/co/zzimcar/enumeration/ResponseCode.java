package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_RETRIEVE_FAILED("BLOG_11119", "블로그 불러오기 실패"),
  BLOG_REFRESH_FAILED("BLOG_11111", "블로그 수정 실패");



  private final String code;
  private final String message;
}
