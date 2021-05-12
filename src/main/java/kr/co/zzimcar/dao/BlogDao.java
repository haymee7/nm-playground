package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.BlogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface BlogDao {
  Optional<BlogDto> retrieveOne(int pid);
}
