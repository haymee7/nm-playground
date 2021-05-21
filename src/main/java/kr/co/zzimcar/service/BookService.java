package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.book.BookReqDto;
import kr.co.zzimcar.dto.book.BookResByCntDto;
import kr.co.zzimcar.dto.book.BookResDto;
import org.springframework.http.ResponseEntity;

public interface BookService {
  ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto);
  ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<BookResByCntDto>> retrieveByCnt(int sp, int cnt, String sort);
  ResponseEntity<ResponseDto<Void>> updateOne(int pid, BookReqDto bookReqDto);
  ResponseEntity<ResponseDto<Void>> deleteOne(int pid);
}
