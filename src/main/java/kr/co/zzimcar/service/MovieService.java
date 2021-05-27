package kr.co.zzimcar.service;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.entity.MoviePageReq;
import kr.co.zzimcar.dto.entity.MovieFindReq;
import kr.co.zzimcar.dto.entity.MovieReq;
import org.springframework.http.ResponseEntity;

public interface MovieService {
  ResponseEntity<ResponseDto<Void>> create(MovieReq movieReq);

  ResponseEntity<ResponseDto<MovieReq>> retrieveOne(int pid);

  ResponseEntity<ResponseDto<MoviePageReq>> retrieveList(int sp, int cnt, String sort);

  ResponseEntity<ResponseDto<MoviePageReq>> retrievePostList(MovieFindReq movieFindReq);

  ResponseEntity<ResponseDto<Void>> delete(int pid);

}
