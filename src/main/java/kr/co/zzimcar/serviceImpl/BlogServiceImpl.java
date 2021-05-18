package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final Logger log = LogManager.getLogger(BlogServiceImpl.class);
  private final BlogDao blogDao;

  @Override
  public ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid) {
    try {
      ResponseDto<BlogResDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(new BlogResDto(blogDao.retrieveOne(pid)));

      return ResponseEntity.ok(responseDto);
    } catch (Exception e) {
      log.info("-- 블로그 정보 불러오기 실패", e);
      throw new ApiException(BLOG_NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<BlogResByCntDto>> retrieveByCnt(int sp, int cnt) {
    try {
      ResponseDto<BlogResByCntDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(new BlogResByCntDto(blogDao.totalCnt(), blogDao.retrieveByCnt(sp, cnt)));

      return ResponseEntity.ok(responseDto);
    } catch (Exception e){
      log.info("-- 블로그 목록 불러오기 실패", e);
      throw new ApiException(BLOG_LIST_FAILED);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto) {
    try {
      blogDao.save(new BlogDto(blogReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 블로그 저장 실패", e);

      // return ResponseEntity.ok(new ResponseDto<>(BLOG_SAVE_FAILED));
      throw new ApiException(BLOG_SAVE_FAILED);
    }
  }
}
