package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.blog.BlogPageResDto;
import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface BlogService {
  ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);
  ResponseEntity<ResponseDto<BlogResDto>> retriveOne(int pid);
  ResponseEntity<ResponseDto<BlogPageResDto>> retriveList(int sp, int cnt, String sort);
}
