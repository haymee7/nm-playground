package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.BlogReqDto;
import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface BlogService {

  ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);

//  ResponseEntity<ResponseDto<BlogDto>> require(BlogReqDto blogReqDto);

  ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto);

}
