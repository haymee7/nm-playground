package kr.co.zzimcar.serviceImpl;

import io.swagger.annotations.Api;
import kr.co.zzimcar.dao.BookDao;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.book.BookDto;
import kr.co.zzimcar.dto.book.BookReqDto;
import kr.co.zzimcar.dto.book.BookListResDto;
import kr.co.zzimcar.dto.book.BookResDto;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BookService;
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
public class BookServiceImpl implements BookService {

  private final Logger log = LogManager.getLogger(BookServiceImpl.class);
  private final BookDao bookDao;

  @Override
  public ResponseEntity<ResponseDto<Void>> create(@Valid BookReqDto bookReqDto) {
    try {
      bookDao.save(new BookDto(bookReqDto));
      return ResponseEntity.ok(new ResponseDto<>(true));
    } catch (Exception e) {
      log.info("-- 책 저장 실패", e);
      throw new ApiException(BOOK_SAVE_FAILED);
    }
  }

  @Override
  public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid) {
    BookDto bookDto = Optional.ofNullable(bookDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(BOOK_RETRIVE_NOT_EXIST));

    ResponseDto<BookResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(new BookResDto(bookDto));

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<BookListResDto>> retrieveByCnt(int sp, int cnt, char sort) {
    checkRetrieveListParams(sp, cnt, sort);
    // TODO: BookResByCntDto -> BookListResDto || BookPagingResDto
    BookListResDto bookListResDto = new BookListResDto();
    bookListResDto.setTotalCnt(bookDao.totalCnt());
    bookListResDto.setList(bookDao.retrieveByCnt(sp, cnt, sort).stream().map(BookResDto::new).collect(Collectors.toList()));

    ResponseDto<BookListResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(bookListResDto);

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> updateOne(int pid, BookReqDto bookReqDto) {
    // TODO: pid 없어도 됨, try-catch 꼭 써야했을까?
    if (bookDao.isExist(pid)==0) throw new ApiException(BOOK_NOT_EXIST);
    bookDao.updateOne(pid, new BookDto(bookReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> deleteOne(int pid) {
    // TODO: isExist 필요했을까?
      bookDao.deleteOne(pid);
      return ResponseEntity.ok(new ResponseDto<>(true));
  }

  private void checkRetrieveListParams(int sp, int cnt, char sort) {
    if (sp < 0) throw new ApiException(COMM_PAGING_REQ_PARAM_INVALID_SP);
    if (cnt < 1) throw new ApiException(COMM_PAGING_REQ_PARAM_INVALID_CNT);
    // TODO: 상수 변수
    if (!"D".equals(sort) && !"A".equals(sort)) throw new ApiException(COMM_PAGING_REQ_PARAM_INVALID_SORT);
  }
}
