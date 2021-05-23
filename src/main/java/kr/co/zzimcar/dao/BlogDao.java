package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.blog.BlogDto;
import kr.co.zzimcar.dto.blog.BlogResDto;
import kr.co.zzimcar.dto.blog.BlogsReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {

    int save(BlogDto blogDto);

    BlogDto retrieveOne(int pid);

    List<BlogResDto> retrieve(BlogsReqDto blogsReqDto);

    int totalCnt();

    List<BlogDto> retriveList(int sp, int cnt, String sort);

    int revice(BlogDto blogDto);

    int delete(int pid);

}
//  List<BlogDto> retrieve(int sp, int cnt);
