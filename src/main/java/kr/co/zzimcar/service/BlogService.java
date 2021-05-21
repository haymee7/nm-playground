package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResByCntDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.book.BookReqDto;
import org.springframework.http.ResponseEntity;

public interface BlogService {
  ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto);
  ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<BlogResByCntDto>> retrieveByCnt(int sp, int cnt);
  ResponseEntity<ResponseDto<Void>> updateOne(int pid, BlogReqDto blogReqDto);
  ResponseEntity<ResponseDto<Void>> deleteOne(int pid);
}
