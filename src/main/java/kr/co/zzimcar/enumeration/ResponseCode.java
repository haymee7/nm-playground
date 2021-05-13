package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_NOT_FOUND("BLOG_10001", "블로그 정보를 찾을 수 없습니다."),
  BLOG_REFRESH_FAILED("BLOG_11111", "블로그 수정 실패");



  private final String code;
  private final String message;
}
