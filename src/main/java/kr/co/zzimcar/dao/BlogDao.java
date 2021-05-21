package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.blog.BlogDto;
import kr.co.zzimcar.dto.book.BookDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
  void save(BlogDto blogDto);
  BlogDto retrieveOne(int pid);
  List<BlogDto> retrieveByCnt(int sp, int cnt);
  int totalCnt();
  int isExist(int pid);
  void updateOne(int pid, BlogDto blogDto);
  void deleteOne(int pid);
}