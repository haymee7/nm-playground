package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.book.BookDataDto;
import kr.co.zzimcar.dto.book.BookReqDto;
import kr.co.zzimcar.dto.book.BookResDto;
import kr.co.zzimcar.dto.book.BooksReqDto;
import kr.co.zzimcar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Api(tags = "Book API")
public class BookController {

  private final BookService bookService;

  @PostMapping("")
  @ApiOperation("POST 책 정보입력 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid BookReqDto bookReqDto) {
    return bookService.create(bookReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("GET 책 정보불러오기 API")
  public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(@PathVariable int pid){
    return bookService.retrieveOne(pid);
  }

  @PostMapping("/list")
  @ApiOperation("POST 책 정보불러오기 API")
  public ResponseEntity<ResponseDto<BookDataDto>> retrieve(@RequestBody @Valid BooksReqDto booksReqDto){
    return bookService.retrieve(booksReqDto);
  }

  @PutMapping("/{pid}")
  @ApiOperation("put 책 정보 수정하기 API")
  public ResponseEntity<ResponseDto<BookReqDto>> revice(@PathVariable int pid, @RequestBody BookReqDto bookReqDto){
    return bookService.revice(pid, bookReqDto);
  }

}
