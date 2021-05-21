package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.blog.BlogDto;
import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResByCntDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.book.BookDto;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final Logger log = LogManager.getLogger(BlogServiceImpl.class);
  private final BlogDao blogDao;

  @Override
  public ResponseEntity<ResponseDto<BlogResDto>> retrieveOne(int pid) {
    // null일수도 아닐수도 있다. null일경우 ApiException 발생시킴
    BlogDto blogDto = Optional.ofNullable(blogDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(BLOG_RETRIVE_NOT_EXIST));

    ResponseDto<BlogResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(new BlogResDto(blogDto));

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<BlogResByCntDto>> retrieveByCnt(int sp, int cnt) {
    checkRetrieveListParams(sp,cnt);
    BlogResByCntDto blogResByCntDto = new BlogResByCntDto();
    blogResByCntDto.setTotalCnt(blogDao.totalCnt());
    // BlogResDto만 모아서 새로운 리스트를 만들어 낼 수 있다.
    blogResByCntDto.setList(blogDao.retrieveByCnt(sp, cnt).stream().map(BlogResDto::new).collect(Collectors.toList()));

    ResponseDto<BlogResByCntDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(blogResByCntDto);
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> create(@Valid BlogReqDto blogReqDto) {

    try {
      blogDao.save(new BlogDto(blogReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 블로그 저장 실패", e);
      throw new ApiException(BLOG_SAVE_FAILED);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> updateOne(int pid, BlogReqDto blogReqDto) {
    if (blogDao.isExist(pid)==0) throw new ApiException(BLOG_NOT_EXIST);
    try {
      blogDao.updateOne(pid, new BlogDto(blogReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 책 수정 실패", e);
      throw new ApiException(BLOG_UPDATE_FAILED);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> deleteOne(int pid) {
    if (blogDao.isExist(pid)==0) throw new ApiException(BLOG_NOT_EXIST);
    try {
      blogDao.deleteOne(pid);
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 블로그 삭제 실패", e);
      throw new ApiException(BLOG_DELETE_FAILED);
    }
  }

  private void checkRetrieveListParams(int sp, int cnt) {
    if (sp < 0) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_SP);
    if (cnt < 1) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_CNT);
  }
}
