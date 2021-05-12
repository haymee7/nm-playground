package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  BLOG_NOT_FOUND("BLOG_10000", "블로그 정보를 찾을 수 없습니다.");

  private final String code;
  private final String message;
}
