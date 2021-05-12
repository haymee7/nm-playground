package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static kr.co.zzimcar.enumeration.ResponseCode.BLOG_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final BlogDao blogDao;

  @Override
  public ResponseEntity<ResponseDto<BlogDto>> retrieveOne(int pid) {
    return ResponseEntity.ok(new ResponseDto<>(blogDao.retrieveOne(pid).orElseThrow(() -> new ApiException(BLOG_NOT_FOUND))));
  }
}
