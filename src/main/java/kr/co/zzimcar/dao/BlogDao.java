package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.blog.BlogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
  void save(BlogDto blogDto);
  BlogDto retriveOne(int pid);
  int getTotalCnt();
  List<BlogDto> retriveList(int sp, int cnt, String sort);
}
