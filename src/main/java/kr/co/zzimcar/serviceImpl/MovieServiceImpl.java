package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.entity.MoviePageReq;
import kr.co.zzimcar.dto.entity.MovieFindReq;
import kr.co.zzimcar.dto.entity.MovieReq;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.repository.MovieEntityRepository;
import kr.co.zzimcar.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
  private final MovieEntityRepository movieEntityRepository;


  @Override
  public ResponseEntity<ResponseDto<Void>> create(MovieReq movieReq) {
    movieEntityRepository.save(movieReq);

    return ResponseEntity.ok(new ResponseDto<>(true));

  }

  @Override
  public ResponseEntity<ResponseDto<MovieReq>> retrieveOne(int pid) {

    Optional<MovieReq> movieReq = movieEntityRepository.findById(pid);

    ResponseDto<MovieReq> responseDto = new ResponseDto<>(true);
    responseDto.setData(movieReq.orElseThrow(() -> new ApiException(MOVIE_RETRIEVEONE_NULL)));

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<MoviePageReq>> retrieveList(int sp, int cnt, String sort) {

    checkRetrieveListParams(sp, cnt, sort);
    Pageable pageable = PageRequest.of(sp, cnt, Sort.by(sort));

    Page<MovieReq> page = movieEntityRepository.findAll(pageable);

    Long totalCnt = page.getTotalElements();

    MoviePageReq moviePageReq = new MoviePageReq();
    moviePageReq.setList(page.getContent().stream().collect(Collectors.toList()));
    moviePageReq.setTotalCnt(totalCnt);

    ResponseDto<MoviePageReq> responseDto = new ResponseDto<>(true);
    responseDto.setData(moviePageReq);

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<MoviePageReq>> retrievePostList(MovieFindReq movieFindReq) {

    CheckRetrieveSortParam(movieFindReq);
    Pageable pageable = PageRequest.of(movieFindReq.getSp(), movieFindReq.getCnt(), Sort.by(movieFindReq.getSort()));

    Page<MovieReq> page = movieEntityRepository.findAll(pageable);

    Long totalCnt = page.getTotalElements();

    MoviePageReq moviePageReq = new MoviePageReq();
    moviePageReq.setList(page.getContent().stream().collect(Collectors.toList()));
    moviePageReq.setTotalCnt(totalCnt);

    ResponseDto<MoviePageReq> responseDto = new ResponseDto<>(true);
    responseDto.setData(moviePageReq);

    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> delete(int pid) {

    checkIntParam(pid);
    movieEntityRepository.deleteById(pid);
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  private void checkRetrieveListParams(int sp, int cnt, String sort) {
    if (sp < 0) throw new ApiException(PAGING_REQ_PARAM_INVALID_SP);
    if (cnt < 1) throw new ApiException(PAGING_REQ_PARAM_INVALID_CNT);
    if (!"pid".equals(sort) && !"director".equals(sort) && !"actor".equals(sort)
      && !"releaseAt".equals(sort) && !"price".equals(sort) && !"title".equals(sort))
      throw new ApiException(MOVIE_PAGING_REQ_PARAM_INVALID_SORT);
  }

  private void CheckRetrieveSortParam(MovieFindReq movieFindReq) {
    if (!"pid".equals(movieFindReq.getSort()) && !"director".equals(movieFindReq.getSort()) && !"actor".equals(movieFindReq.getSort())
      && !"releaseAt".equals(movieFindReq.getSort()) && !"price".equals(movieFindReq.getSort()) && !"title".equals(movieFindReq.getSort()))
      throw new ApiException(MOVIE_PAGING_REQ_PARAM_INVALID_SORT);
  }

  private void checkIntParam(int pid) {
    if (pid <= 0) throw new ApiException(LOAD_PID);
  }
}
