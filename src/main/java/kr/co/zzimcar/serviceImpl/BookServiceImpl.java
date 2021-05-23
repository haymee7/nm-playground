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

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final BookDao bookDao;

    @Override
    public ResponseEntity<ResponseDto<Void>> create(BookReqDto bookReqDto) {
        // TODO: try-catch 쓴 이유는?
        try {
            bookDao.upload(new BookDto(bookReqDto));
            return ResponseEntity.ok(new ResponseDto<>(true));
        } catch (Exception e) {
            log.info(" --책 정보 업로드 실패", e);
            throw new ApiException(BOOK_UPLOAD_FAILED);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<BookResDto>> retrieveOne(int pid) {
        checkIntParams(pid);    // TODO controller 에서 @valid 걸어서 처리하거나 생략해도 됨
        try {
            BookDto bookDto = bookDao.retrieveOne(pid);
            if (bookDto == null) throw new ApiException(BOOK_RETRIEVEONE_NULL); // TODO: 이 예외처리가 의도한대로 될까?
            BookResDto bookResDto = new BookResDto(bookDto);
            ResponseDto<BookResDto> responseDto = new ResponseDto<>(true);
            responseDto.setData(bookResDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.info(" --책 정보 pid로 불러오기 실패", e);
            throw new ApiException(BOOK_RETRIEVEONE_FAILED);

        }
    }

    @Override
    public ResponseEntity<ResponseDto<BookDataDto>> retrieve(BooksReqDto booksReqDto) {

        // TODO: dao 에서 resDto 로 바로 받고있음. dao 에서 dto 로 받아서 resDto 로 변환하도록 수정해야 함.
        // TODO: SQL Exception 핸들러가 없는데 try-catch 도 없음. 쿼리 에러 발생하면 API 응답이 어떻게 될까?
        List<BookResDto> bookDto = bookDao.retrieve(booksReqDto);
        if (bookDto == null) throw new ApiException(BOOK_RETRIEVE_NULL);
        int totalCnt = bookDao.totalCnt();
        BookDataDto dataDto = new BookDataDto(bookDto, totalCnt);
        ResponseDto<BookDataDto> responseDto = new ResponseDto<>(true);
        responseDto.setData(dataDto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    // TODO: revice 는 무슨 의미지? 함수명 지을 때 해당 함수가 어떤일을 하는 함수인지 이름만 보고도 알 수 있어야 함. revice 는 단어도 아님..
    public ResponseEntity<ResponseDto<Void>> revice(int pid, BookReqDto bookReqDto) {
        checkIntParams(pid);
        // TODO: try-catch 굳이 써야했을까
        try {
            bookDao.revice(new BookDto(pid, bookReqDto));
            return ResponseEntity.ok(new ResponseDto<>(true));
        } catch (Exception e) {
            log.info(" --책 정보 수정 실패", e);
            throw new ApiException(BOOK_REVICE_FAILED);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> erase(int pid) {
        // TODO: try-catch 굳이 써야했을까
        try {
            bookDao.delete(pid);
            return ResponseEntity.ok(new ResponseDto<>(true));
        } catch (Exception e) {
            log.info(" --책 정보 삭제 실패", e);
            throw new ApiException(BOOK_ERASE_FAILED);
        }

    }

    // TODO: 생략 가능한 함수로 보임
    private void checkIntParams(int pid) {
        if (pid <= 0) throw new ApiException(BOOK_LOAD_PID);
    }

}

