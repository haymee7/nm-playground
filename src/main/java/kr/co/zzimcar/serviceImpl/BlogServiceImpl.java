package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.blog.BlogDto;
import kr.co.zzimcar.dto.blog.BlogPageResDto;
import kr.co.zzimcar.dto.blog.BlogReqDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final Logger log = LogManager.getLogger(BlogServiceImpl.class);
  private final BlogDao blogDao;

  @Override
  public ResponseEntity<ResponseDto<BlogPageResDto>> retriveList(int sp, int cnt, String sort) {
    checkRetrieveListParams(sp, cnt, sort);
    BlogPageResDto blogPageResDto = new BlogPageResDto();
    blogPageResDto.setTotalCnt(blogDao.getTotalCnt());
    blogPageResDto.setList(blogDao.retriveList(sp, cnt, sort).stream().map(BlogResDto::new).collect(Collectors.toList()));

    ResponseDto<BlogPageResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(blogPageResDto);
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<BlogResDto>> retriveOne(int pid) {
    BlogDto blogDto = Optional.of(blogDao.retriveOne(pid)).orElseThrow(() -> new ApiException(BLOG_RETRIVE_NOT_EXIST));
    BlogResDto blogResDto = new BlogResDto(blogDto);
    ResponseDto<BlogResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(blogResDto);

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> create(BlogReqDto blogReqDto) {
    try {
      blogDao.save(new BlogDto(blogReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 블로그 저장 실패", e);
      throw new ApiException(BLOG_SAVE_FAILED);
    }
  }


  private void checkRetrieveListParams(int sp, int cnt, String sort) {
    if (sp < 0) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_SP);
    if (cnt < 1) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_CNT);
    if (!"D".equals(sort) && !"A".equals(sort)) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_SORT);
  }
}
