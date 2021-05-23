package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.book.BookDataDto;
import kr.co.zzimcar.dto.book.BookReqDto;
import kr.co.zzimcar.dto.book.BookResDto;
import kr.co.zzimcar.dto.book.BooksReqDto;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto);

    ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid);

    ResponseEntity<ResponseDto<BookDataDto>> retrieve(BooksReqDto booksReqDto);

    ResponseEntity<ResponseDto<Void>> revice(int pid, BookReqDto bookReqDto);

    ResponseEntity<ResponseDto<Void>> erase(int pid);
}
