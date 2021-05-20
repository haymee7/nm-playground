package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.blog.BlogDataDto;
import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.blog.BlogsReqDto;
import org.springframework.http.ResponseEntity;


public interface BlogService {
    ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);

    ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid);

    ResponseEntity<ResponseDto<BlogDataDto>> retrieve(BlogsReqDto blogsReqDto);

    ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto);

}


//  ResponseEntity<ResponseDto<DataDto>> retrieve(int sp, int cnt);
