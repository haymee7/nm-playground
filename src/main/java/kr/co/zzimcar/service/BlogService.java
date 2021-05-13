package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.BlogReqDto;
import kr.co.zzimcar.dto.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {
    ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);

    ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid);

    ResponseEntity<ResponseDto<List<BlogResDto>>> retrieve(int sp, int cnt);

    ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto);

}
