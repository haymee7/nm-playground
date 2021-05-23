package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    BLOG_SAVE_FAILED("BLOG_0001", "블로그 저장 실패"),
    BLOG_RETRIEVEONE_NULL("BOOK_0002", "블로그 데이터(RETRIEVEONE) null"),
    BLOG_RETRIEVE_FAILED("BLOG_0003", "PID를 확인하세요"),
    BLOG_RETRIEVE_NULL("BOOK_0004", "블로그 데이터(RETRIEVE) null"),
    BLOG_INTRETRIEVE_FAILED("BLOG_0005", "블로그 통합 수정 실패"),
    BLOG_REVICE_FAILED("BLOG_0006", "블로그 수정 실패"),
    BLOG_ERASE_FAILED("BOOK_0007", "블로그 정보 삭제 실패"),
    BLOG_LOAD_PID("BLOG_0008", "pid를 1이상의 값을 입력하세요"),
    BLOG_PAGING_REQ_PARAM_INVALID_SP("BLOG_0009", "sp는 0보다 큰 값이어야 합니다."),
    BLOG_PAGING_REQ_PARAM_INVALID_CNT("BLOG_0010", "cnt는 0보다 큰 값이어야 합니다."),
    BLOG_PAGING_REQ_PARAM_INVALID_SORT("BLOG_0011", "sort는 D 또는 A 여야함"),

    BOOK_UPLOAD_FAILED("BOOK_0001", "책 업로드 실패"),
    BOOK_RETRIEVEONE_FAILED("BOOK_0002", "PID를 확인하세요"),
    BOOK_RETRIEVEONE_NULL("BOOK_0003", "책정보 데이터(RETRIEVEONE) null"),
    BOOK_RETRIEVE_FAILED("BOOK_0004", "책 정보 순서로 불러오기 실패"),
    BOOK_RETRIEVE_NULL("BOOK_0005", "책정보 데이터(RETRIEVE) null"),
    BOOK_RETRIEVE_ORDER("BOOK_0006", "DESC나 ASC를 입력하세요"),
    BOOK_REVICE_FAILED("BOOK_0007", "책 정보 수정 실패"),
    BOOK_ERASE_FAILED("BOOK_0008", "책 정보 삭제 실패"),
    BOOK_LOAD_PID("BOOK_0009", "pid를 1이상의 값을 입력하세요");


    private final String code;
    private final String message;
}
