package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.BookReqDto;
import kr.co.zzimcar.dto.BookResByCntDto;
import kr.co.zzimcar.dto.BookResDto;
import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface BookService {
  ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto);
  ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<BookResByCntDto>> retrieveByCnt(int sp, int cnt, String sort);
}
