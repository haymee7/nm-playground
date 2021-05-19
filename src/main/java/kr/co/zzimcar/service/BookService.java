package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import org.springframework.http.ResponseEntity;

public interface BookService {
  ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto);
  ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<BookDataDto>> retrieve(BooksReqDto booksReqDto);
  ResponseEntity<ResponseDto<BookReqDto>> revice(int pid, BookReqDto bookReqDto);
}
