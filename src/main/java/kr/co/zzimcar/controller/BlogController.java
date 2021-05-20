package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.dto.blog.BlogPageResDto;
import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Api(tags = "블로그 API")
@Validated
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  @ApiOperation("포스트 작성 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid BlogReqDto blogReqDto) {
    return blogService.create(blogReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("포스트 조회 API")
  public ResponseEntity<ResponseDto<BlogResDto>> retriveOne(@PathVariable int pid) {
    return blogService.retriveOne(pid);
  }

  @GetMapping("")
  @ApiOperation("포스트 리스트 조회 API")
  public ResponseEntity<ResponseDto<BlogPageResDto>> retriveList(@RequestParam("sp")
                                                                 @ApiParam("start point") int sp,
                                                                 @RequestParam("cnt") int cnt,
                                                                 @RequestParam("sort") @ApiParam("DESC: D, ASC: A") String sort) {
    return blogService.retriveList(sp, cnt, sort);

  }

}
