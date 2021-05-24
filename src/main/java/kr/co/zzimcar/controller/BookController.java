package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.book.BookReqDto;
import kr.co.zzimcar.dto.book.BookListResDto;
import kr.co.zzimcar.dto.book.BookResDto;
import kr.co.zzimcar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Api(tags = "책 API")
@Validated
public class BookController {

  private final BookService bookService;

  @PostMapping("")
  @ApiOperation("책 작성 API")
  // TODO: 공통과제1(validation 에러 여러개인 경우 string 으로 이어서 message 로 전달하기(이쁘게)) - 안되어 있음
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid @ApiParam(value = "저장할 책 정보", required = true) BookReqDto bookReqDto) {
    return bookService.create(bookReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("책 조회 API")
  // TODO: 파라미터 타입 익셉션 처리 해야함
  public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(@PathVariable @Valid @ApiParam(value = "책 번호", required = true, example = "1") int pid) {
    return bookService.retrieveOne(pid);
  }

  @GetMapping("")
  @ApiOperation("책 목록 불러오기")
  public ResponseEntity<ResponseDto<BookListResDto>> retrieveByCnt(@RequestParam("sp") @ApiParam(value = "시작 인덱스", required = true, example = "1") int sp,
                                                                   @RequestParam("cnt") @ApiParam(value = "불러올 글 갯수", required = true, example = "5") int cnt,
                                                                   @RequestParam("sort") @ApiParam(value = "정렬 순서(최신순 = D, 오래된순 = A)", required = true, example = "D") char sort) {
    return bookService.retrieveByCnt(sp, cnt, sort);
  }

  @PutMapping("/{pid}")
  @ApiOperation("책 수정 API")
  public ResponseEntity<ResponseDto<Void>> updateOne(@PathVariable @ApiParam(value = "수정할 책 번호", required = true, example = "1") int pid,
                                                     @RequestBody @Valid @ApiParam(value = "수정할 책 정보", required = true) BookReqDto bookReqDto) {
    return bookService.updateOne(pid, bookReqDto);
  }

  @DeleteMapping("/{pid}")
  @ApiOperation("책 삭제 API")
  public ResponseEntity<ResponseDto<Void>> deleteOne(@PathVariable @ApiParam(value = "삭제할 책 정보", required = true, example = "1") int pid) {
    return bookService.deleteOne(pid);
  }
}