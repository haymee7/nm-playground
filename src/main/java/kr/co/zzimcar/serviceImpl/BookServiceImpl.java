package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BookDao;
import kr.co.zzimcar.dto.*;
import kr.co.zzimcar.dto.book.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final Logger log = LogManager.getLogger(BookServiceImpl.class);
  private final BookDao bookDao;


  @Override
  public ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto) {

    bookDao.upload(new BookDto(bookReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));

  }


  @Override
  public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid) {


      BookDto bookDto = bookDao.retrieveOne(pid);
      BookResDto bookResDto = new BookResDto(bookDto);
      ResponseDto<BookResDto> responseDto = new ResponseDto<>(true);
      responseDto.setData(bookResDto);
      return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<BookDataDto>> retrieve(BooksReqDto booksReqDto) {

    checkOrderVaild(booksReqDto);
    BookDataDto bookDataDto = new BookDataDto();
    bookDataDto.setTotalCnt(bookDao.totalCnt());
    bookDataDto.setList(bookDao.retrieve(booksReqDto).stream().map(BookDto -> new BookResDto(BookDto)).collect(Collectors.toList()));
    Optional<List> opt = Optional.ofNullable(bookDataDto.getList());
    System.out.println("Optional= " + opt.isPresent());
    ResponseDto<BookDataDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(bookDataDto);
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> update(BookReqDto bookReqDto) {

    bookDao.update(new BookDto(bookReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> delete(int pid) {
    checkIntParams(pid);
    bookDao.delete(pid);
    return ResponseEntity.ok(new ResponseDto<>(true));
  }


  private void checkOrderVaild(BooksReqDto booksReqDto) {
    if (!"desc".equals(booksReqDto.getOrder()) && !"asc".equals(booksReqDto.getOrder())) throw new ApiException(BOOK_PAGING_INVAILD_ORDER);
  }

  private void checkIntParams(int pid) {
    if (pid <= 0) throw new ApiException(LOAD_PID);
  }
}

