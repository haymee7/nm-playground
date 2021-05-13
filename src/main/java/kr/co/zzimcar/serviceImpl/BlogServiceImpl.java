package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.dto.BlogReqDto;
import kr.co.zzimcar.dto.BlogResDto;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResponseEntity<ResponseDto<List<BlogResDto>>> retrieve(int sp, int cnt) {
        List<BlogDto> blogDto = blogDao.retrieve(sp, cnt);
        List<BlogResDto> blogResDto = new ArrayList<>();
        ResponseDto<List<BlogResDto>> responseDto = new ResponseDto<>(true);
        responseDto.setData(blogResDto);

        return ResponseEntity.ok(responseDto);
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

