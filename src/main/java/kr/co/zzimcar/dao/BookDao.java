package kr.co.zzimcar.dao;


import kr.co.zzimcar.dto.BookDto;
import kr.co.zzimcar.dto.BooksReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {
  int upload(BookDto bookDto);
  BookDto retrieveOne(int pid);
  List<BookDto> retrieve(BooksReqDto booksReqDto);
  int totalCnt();

}
