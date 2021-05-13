package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.zzimcar.dto.BlogReqDto;
import kr.co.zzimcar.dto.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Api(tags = "블로그 API")
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  @ApiOperation("포스트 작성 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid BlogReqDto blogReqDto) {
    return blogService.create(blogReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("포스트 조회 API")
  public ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(@PathVariable int pid) {
    return blogService.retrieveOne(pid);
  }

  @GetMapping("blogs/{sp},{cnt}")
  @ApiOperation("포스트 통합조회 API")
  public ResponseEntity<ResponseDto<List<BlogResDto>>> retrieve(@PathVariable int sp, @PathVariable int cnt) {
    return blogService.retrieve(sp, cnt);
  }

  @PutMapping("/{pid}")
  public ResponseEntity<ResponseDto<BlogReqDto>> revice(@PathVariable int pid, @RequestBody BlogReqDto blogReqDto) {
    return blogService.revice(pid, blogReqDto);
  }
}
