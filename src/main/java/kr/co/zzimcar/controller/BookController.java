package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ResponseEntity<ResponseDto<Void>> create(@RequestBody @ApiParam(value = "저장할 책 정보", required = true) @Valid BookReqDto bookReqDto) {
        return bookService.create(bookReqDto);
    }

    @GetMapping("/{pid}")
    @ApiOperation("GET 책 정보불러오기 API")
    public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(@PathVariable @ApiParam(value = "불러올 책의 번호", required = true) int pid) {
        return bookService.retrieveOne(pid);
    }

    @PostMapping("/list")
    @ApiOperation("POST 책 정보불러오기 API")
    public ResponseEntity<ResponseDto<BookDataDto>> retrieve(@RequestBody @ApiParam(value = "불러올 책의 정보", required = true) @Valid BooksReqDto booksReqDto) {
        return bookService.retrieve(booksReqDto);
    }

    @PutMapping("/{pid}")
    @ApiOperation("put 책 정보 수정하기 API")
    // TODO: pid 와 bookReqDto 따로 받은 이유 짐작은 되나 그럴필요 없음.
    public ResponseEntity<ResponseDto<Void>> revice(@PathVariable @ApiParam(value = "수정할 책의 번호", required = true) int pid, @RequestBody @ApiParam(value = "수정할 책의 정보", required = true) @Valid BookReqDto bookReqDto) {
        return bookService.revice(pid, bookReqDto);
    }

    @DeleteMapping("/{pid}")
    @ApiOperation("DELETE 책 정보 삭제하기 API")
    // TODO: 삭제 관련한 함수명은 보통 delete 단어를 사용함.
    public ResponseEntity<ResponseDto<Void>> erase(@PathVariable @ApiParam(value = "삭제할 책의 번호", required = true) int pid) {
        return bookService.erase(pid);
    }

}
