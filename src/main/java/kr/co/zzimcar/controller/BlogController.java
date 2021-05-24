package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.blog.*;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Api(tags = "블로그 API")
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  @ApiOperation("포스트 작성 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @ApiParam(value = "저장할 블로그 정보", required = true) @Valid BlogReqDto blogReqDto) {
    return blogService.create(blogReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("포스트 조회 API")
  public ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(@PathVariable @ApiParam(value = "불러올 블로그 번호", required = true) int pid) {
    return blogService.retrieveOne(pid);
  }


  @PostMapping("/list")
  @ApiOperation("포스트 통합조회 API")
  public ResponseEntity<ResponseDto<BlogDataDto>> retrieve(@RequestBody @ApiParam(value = "불러올 블로그 리스트", required = true) BlogsReqDto blogsReqDto) {
    return blogService.retrieve(blogsReqDto);
  }

  @GetMapping("")
  @ApiOperation("포스트 리스트 조회 API")
  public ResponseEntity<ResponseDto<BlogPageResDto>> retriveList(@RequestParam("sp")
                                                                 @ApiParam("start point") int sp,
                                                                 @RequestParam("cnt") int cnt,
                                                                 @RequestParam("sort") @ApiParam("DESC: D, ASC: A") String sort) {
    return blogService.retriveList(sp, cnt, sort);

  }

  @PutMapping("/{pid}")
  @ApiOperation("포스트 수정 API")
  public ResponseEntity<ResponseDto<BlogReqDto>> revice(@PathVariable @ApiParam(value = "불러올 블로그 번호", required = true) int pid, @RequestBody @ApiParam(value = "수정할 블로그 정보", required = true) BlogReqDto blogReqDto) {
    return blogService.revice(pid, blogReqDto);
  }

  @DeleteMapping("/{pid}")
  @ApiOperation("포스트 삭제 API")
  public ResponseEntity<ResponseDto<Void>> erase(@PathVariable @ApiParam(value = "삭제할 블로그의 번호", required = true) int pid) {
    return blogService.erase(pid);
  }


}

