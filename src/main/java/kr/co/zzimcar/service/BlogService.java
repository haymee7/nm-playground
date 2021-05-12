package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface BlogService {
  ResponseEntity<ResponseDto<BlogDto>> retrieveOne(int pid);
}
