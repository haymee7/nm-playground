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
  public ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto) {
    try {
      blogDao.save(new BlogDto(blogReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 블로그 저장 실패", e);

      // return ResponseEntity.ok(new ResponseDto<>(BLOG_SAVE_FAILED));
      throw new ApiException(BLOG_SAVE_FAILED);   //import enumeration으로 시켜줌
    }
  }

  @Override
  public ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid) {
    try {
      BlogDto blogDto = blogDao.retrieveOne(pid);
      BlogResDto blogResDto = new BlogResDto(blogDto);
      ResponseDto<BlogResDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(blogResDto);

      return ResponseEntity.ok(responseDto);
    } catch (Exception e) {
      log.info("-- 블로그 불러오기 실패", e);
      throw new ApiException(BLOG_RETRIEVE_FAILED);
    }
  }


  @Override
  public ResponseEntity<ResponseDto<DataDto>> retrieve(BlogsReqDto blogsReqDto) {
    try {
      List<BlogDto> blogDto = blogDao.retrieve(blogsReqDto);
      int totalCnt = blogDao.totalCnt();
      DataDto dataDto = new DataDto(blogDto, totalCnt);

      ResponseDto<DataDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(dataDto);

      return ResponseEntity.ok(responseDto);
    } catch (Exception e) {
      log.info("-- 블로그 통합조회 실패", e);
      throw new ApiException(BLOG_INTRETRIEVE_FAILED);
    }
  }


  @Override
  public ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto) {
    try {
      blogDao.refresh(new BlogDto(pid, blogReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 블로그 수정 실패", e);
      throw new ApiException(BLOG_REFRESH_FAILED);
    }
  }
}

//  @Override
//  public ResponseEntity<ResponseDto<DataDto>> retrieve(int sp, int cnt) {
//    try {
//      List<BlogDto> blogDto = blogDao.retrieve(sp, cnt);
//      int totalCnt = blogDao.totalCnt();
//      DataDto dataDto = new DataDto(blogDto, totalCnt);
//
//      ResponseDto<DataDto> responseDto = new ResponseDto<>(true);
//      responseDto.setData(dataDto);
//
//      return ResponseEntity.ok(responseDto);
//    } catch (Exception e) {
//      log.info("-- 블로그 통합조회 실패", e);
//      throw new ApiException(BLOG_INTRETRIEVE_FAILED);
//    }
//  }
