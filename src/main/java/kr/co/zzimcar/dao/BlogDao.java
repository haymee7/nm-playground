package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.dto.BlogsReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {

  int save(BlogDto blogDto);

  BlogDto retrieveOne(int pid);

  List<BlogDto> retrieve(BlogsReqDto blogsReqDto);

  int totalCnt();

  int revice(BlogDto blogDto);

}
//  List<BlogDto> retrieve(int sp, int cnt);
