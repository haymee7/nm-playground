package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.BlogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
  int save(BlogDto blogDto);
  BlogDto retrieveOne(int pid);
  List<BlogDto> retrieve(int sp, int cnt);
  int refresh(BlogDto blogDto);

}
