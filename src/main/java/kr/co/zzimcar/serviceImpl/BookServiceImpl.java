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
        checkIntParams(pid);
        try {
            BookDto bookDto = bookDao.retrieveOne(pid);
            if (bookDto == null) throw new ApiException(BOOK_RETRIEVEONE_NULL);
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

        List<BookResDto> bookDto = bookDao.retrieve(booksReqDto);
        if (bookDto == null) throw new ApiException(BOOK_RETRIEVE_NULL);
        int totalCnt = bookDao.totalCnt();
        BookDataDto dataDto = new BookDataDto(bookDto, totalCnt);
        ResponseDto<BookDataDto> responseDto = new ResponseDto<>(true);
        responseDto.setData(dataDto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> revice(int pid, BookReqDto bookReqDto) {
        checkIntParams(pid);
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
        try {
            bookDao.delete(pid);
            return ResponseEntity.ok(new ResponseDto<>(true));
        } catch (Exception e) {
            log.info(" --책 정보 삭제 실패", e);
            throw new ApiException(BOOK_ERASE_FAILED);
        }

    }

    private void checkIntParams(int pid) {
        if (pid <= 0) throw new ApiException(BOOK_LOAD_PID);
    }

}

