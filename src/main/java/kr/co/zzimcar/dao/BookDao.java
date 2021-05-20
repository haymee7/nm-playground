package kr.co.zzimcar.dao;


import kr.co.zzimcar.dto.book.BookDto;
import kr.co.zzimcar.dto.book.BookResDto;
import kr.co.zzimcar.dto.book.BooksReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {
    int upload(BookDto bookDto);

    BookDto retrieveOne(int pid);

    List<BookResDto> retrieve(BooksReqDto booksReqDto);

    int totalCnt();

    int revice(BookDto bookDto);

}
