package kr.co.zzimcar.controller;

import io.swagger.annotations.Api;
import kr.co.zzimcar.dto.BlogReqDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Api(tags = "블로그 API")
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody BlogReqDto blogReqDto) {
    return blogService.create(blogReqDto);
  }

  //  @GetMapping("")
  //  public ResponseEntity<ResponseDto<BlogDto>> require(BlogReqDto blogReqDto){
  //    return blogService.require(blogReqDto);
  //  }

  @PutMapping("/{pid}")
  public ResponseEntity<ResponseDto<BlogReqDto>> revice(@PathVariable int pid,@RequestBody BlogReqDto blogReqDto) {
    return blogService.revice(pid, blogReqDto);
  }


}

