package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BookDao;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final Logger log = LogManager.getLogger(BookServiceImpl.class);
  private final BookDao bookDao;

  @Override
  public ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto) {
    try {
      bookDao.save(new BookDto(bookReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 책 저장 실패", e);
      throw new ApiException(BOOK_SAVE_FAILED);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<BookDto>> retrieveOne(int pid) {
    try {
//      ResponseDto<BookResDto> responseDto = new ResponseDto<>(true);
//      responseDto.setData(new BookResDto(bookDao.retrieveOne(pid)));
      // 이거 꼭 물어보기
      ResponseDto<BookDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(new BookDto(bookDao.retrieveOne(pid)));

      return ResponseEntity.ok(responseDto);
    } catch (Exception e) {
      log.info("-- 책 정보 불러오기 실패");
      throw new ApiException(BOOK_NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<BookResByCntDto>> retrieveByCnt(int sp, int cnt, String sort) {
    try {
      ResponseDto<BookResByCntDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(new BookResByCntDto(bookDao.totalCnt(), bookDao.retrieveByCnt(sp, cnt, sort.equals("n") ? "DESC" : "ASC")));

      return ResponseEntity.ok(responseDto);
    } catch (Exception e) {
      log.info("-- 책 목록 불러오기 실패");
      throw new ApiException(BOOK_LIST_FAILED);
    }
  }
}
