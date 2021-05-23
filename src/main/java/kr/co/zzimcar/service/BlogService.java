package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.blog.*;
import org.springframework.http.ResponseEntity;


public interface BlogService {
    ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);

    ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid);

    ResponseEntity<ResponseDto<BlogDataDto>> retrieve(BlogsReqDto blogsReqDto);

    ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto);

    ResponseEntity<ResponseDto<BlogPageResDto>> retriveList(int sp, int cnt, String sort);

    ResponseEntity<ResponseDto<Void>> erase(int pid);

}


//  ResponseEntity<ResponseDto<DataDto>> retrieve(int sp, int cnt);
