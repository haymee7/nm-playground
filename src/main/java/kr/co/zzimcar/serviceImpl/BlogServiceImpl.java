package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.blog.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        checkIntParams(pid);
        try {
            BlogDto blogDto = blogDao.retrieveOne(pid);
            if (blogDto == null) throw new ApiException(BLOG_RETRIEVEONE_NULL);
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
    public ResponseEntity<ResponseDto<BlogDataDto>> retrieve(BlogsReqDto blogsReqDto) {
        try {
            List<BlogResDto> blogDto = blogDao.retrieve(blogsReqDto);
            if (blogDto == null) throw new ApiException(BLOG_RETRIEVE_NULL);

            int totalCnt = blogDao.totalCnt();
            BlogDataDto dataDto = new BlogDataDto(blogDto, totalCnt);

            ResponseDto<BlogDataDto> responseDto = new ResponseDto<>(true);
            responseDto.setData(dataDto);

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.info("-- 블로그 통합조회 실패", e);
            throw new ApiException(BLOG_INTRETRIEVE_FAILED);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<BlogPageResDto>> retriveList(int sp, int cnt, String sort) {
        checkRetrieveListParams(sp, cnt, sort);
        BlogPageResDto blogPageResDto = new BlogPageResDto();
        blogPageResDto.setTotalCnt(blogDao.totalCnt());
        blogPageResDto.setList(blogDao.retriveList(sp, cnt, sort).stream().map(BlogResDto::new).collect(Collectors.toList()));
        ResponseDto<BlogPageResDto> responseDto = new ResponseDto<>(true);
        responseDto.setData(blogPageResDto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<ResponseDto<BlogReqDto>> revice(int pid, BlogReqDto blogReqDto) {
        checkIntParams(pid);
        try {
            blogDao.revice(new BlogDto(pid, blogReqDto));
            return ResponseEntity.ok(new ResponseDto<>(true));
        } catch (Exception e) {
            log.info("-- 블로그 수정 실패", e);
            throw new ApiException(BLOG_REVICE_FAILED);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> erase(int pid) {
        try {
            blogDao.delete(pid);
            return ResponseEntity.ok(new ResponseDto<>(true));
        } catch (Exception e) {
            log.info(" --블로그 정보 삭제 실패", e);
            throw new ApiException(BLOG_ERASE_FAILED);
        }

    }

    private void checkRetrieveListParams(int sp, int cnt, String sort) {
        if (sp < 0) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_SP);
        if (cnt < 1) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_CNT);
        if (!"D".equals(sort) && !"A".equals(sort)) throw new ApiException(BLOG_PAGING_REQ_PARAM_INVALID_SORT);
    }

    private void checkIntParams(int pid) {
        if (pid <= 0) throw new ApiException(BLOG_LOAD_PID);
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
