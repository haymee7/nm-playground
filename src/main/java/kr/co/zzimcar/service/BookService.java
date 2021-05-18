package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import org.springframework.http.ResponseEntity;

public interface BookService {
  ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto);
  ResponseEntity<ResponseDto<BookDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<BookResByCntDto>> retrieveByCnt(int sp, int cnt, String sort);
}
