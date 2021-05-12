package kr.co.zzimcar.controller;

import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;

  @GetMapping("/{pid}")
  public ResponseEntity<ResponseDto<BlogDto>> retrieveOne(@PathVariable("pid") int pid) {
    return blogService.retrieveOne(pid);
  }
}