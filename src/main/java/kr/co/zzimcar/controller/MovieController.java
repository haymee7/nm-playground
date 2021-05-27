package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.entity.MoviePageReq;
import kr.co.zzimcar.dto.entity.MovieFindReq;
import kr.co.zzimcar.dto.entity.MovieReq;
import kr.co.zzimcar.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Api(tags = "Movie API")
public class MovieController {

  private final MovieService movieService;

  @PostMapping("")
  @ApiOperation("영화 정보입력 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @ApiParam(value = "저장할 영화 정보", required = true) @Valid MovieReq movieReq) {
    return movieService.create(movieReq);
  }

  @GetMapping("/{pid}")
  @ApiOperation("영화 불러오기")
  public ResponseEntity<ResponseDto<MovieReq>> retrieveOne(@PathVariable int pid) {
    return movieService.retrieveOne(pid);
  }

  @GetMapping("/list")
  @ApiOperation("영화 Get list불러오기")
  public ResponseEntity<ResponseDto<MoviePageReq>> retrieveList(@RequestParam(value = "sp", defaultValue = "0")
                                                     @ApiParam("start point") int sp,
                                                                @RequestParam("cnt") int cnt,
                                                                @RequestParam(value = "sort", defaultValue = "pid") @ApiParam("pid, director, actor, releaseAt, price, title") String sort) {
    return movieService.retrieveList(sp, cnt, sort);

  }

  @PostMapping("/lists")
  @ApiOperation("영화 Post list 불러오기")
  public ResponseEntity<ResponseDto<MoviePageReq>> retrievePostList(@RequestBody @Valid MovieFindReq movieFindReq) {
    return movieService.retrievePostList(movieFindReq);
  }

  @DeleteMapping("/delete/{pid}")
  @ApiOperation("영화 삭제")
  public ResponseEntity<ResponseDto<Void>> delete(@PathVariable int pid) {
    return movieService.delete(pid);
  }


}
