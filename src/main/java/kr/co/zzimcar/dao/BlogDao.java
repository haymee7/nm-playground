package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.BlogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogDao {

  int save(BlogDto blogDto);
  int refresh(BlogDto blogDto);
}
