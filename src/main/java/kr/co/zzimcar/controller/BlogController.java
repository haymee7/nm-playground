package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResByCntDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.book.BookReqDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Api(tags = "블로그 API")
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  @ApiOperation("블로그 작성 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid @ApiParam(value = "저장할 블로그 정보", required = true) BlogReqDto blogReqDto) {

    return blogService.create(blogReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("블로그 하나 조회 API")
  public ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(@PathVariable @ApiParam(value = "블로그 글 번호", required = true, example = "1") int pid) {
    return blogService.retrieveOne(pid);
  }

  @GetMapping("")
  @ApiOperation("블로그 목록 API")
  public ResponseEntity<ResponseDto<BlogResByCntDto>> retrieveByCnt(@RequestParam("sp") @ApiParam(value = "시작번호", required = true, example = "1") int sp,
                                                                    @RequestParam("cnt") @ApiParam(value = "불러올갯수", required = true, example = "5") int cnt) {
    return blogService.retrieveByCnt(sp,cnt);
  }

  @PutMapping("/{pid}")
  @ApiOperation("블로그 수정 API")
  public ResponseEntity<ResponseDto<Void>> updateOne(@PathVariable @ApiParam(value = "수정할 블로그 번호", required = true, example = "1") int pid,
                                                     @RequestBody @Valid @ApiParam(value = "수정할 블로그 정보", required = true) BlogReqDto blogReqDto) {
    return blogService.updateOne(pid, blogReqDto);
  }

  @DeleteMapping("/{pid}")
  @ApiOperation("블로그 삭제 API")
  public ResponseEntity<ResponseDto<Void>> deleteOne(@PathVariable @ApiParam(value = "삭제할 블로그 정보", required = true, example = "1") int pid) {
    return blogService.deleteOne(pid);
  }
}