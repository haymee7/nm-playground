package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.book.BookDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {
  void save(BookDto bookDto);
  BookDto retrieveOne(int pid);
  List<BookDto> retrieveByCnt(int sp, int cnt, char sort);
  int totalCnt();
  int isExist(int pid);
  void updateOne(int pid, BookDto bookDto);
  void deleteOne(int pid);
}