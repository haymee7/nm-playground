package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.dto.BookReqDto;
import kr.co.zzimcar.dto.BookResByCntDto;
import kr.co.zzimcar.dto.BookResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Api(tags = "책 API")
public class BookController {

  private final BookService bookService;

  @PostMapping("")
  @ApiOperation("책 작성 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @ApiParam(value = "저장할 책 정보", required = true) BookReqDto bookReqDto) {
    return bookService.create(bookReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("책 조회 API")
  public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(@PathVariable @ApiParam(value = "책 번호", required = true, example = "1") int pid) {
    return bookService.retrieveOne(pid);
  }

  @PostMapping("/{sp}/{cnt}/{order}")
  @ApiOperation("책 목록 불러오기")
  public ResponseEntity<ResponseDto<BookResByCntDto>> retrieveByCnt(@PathVariable @ApiParam(value = "시작 인덱스", required = true, example = "1") int sp,
                                                                    @PathVariable @ApiParam(value = "불러올 글 갯수", required = true, example = "5") int cnt,
                                                                    @RequestParam @ApiParam(value = "정렬 순서(최신순 = n, 오래된순 = o)", required = true, example = "n") String sort) {
    return bookService.retrieveByCnt(sp, cnt, sort);
  }
}