package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import org.springframework.http.ResponseEntity;


public interface BlogService {
  ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);

  ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid);

  ResponseEntity<ResponseDto<DataDto>> retrieve(BlogsReqDto blogsReqDto);

  ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto);

}


//  ResponseEntity<ResponseDto<DataDto>> retrieve(int sp, int cnt);
