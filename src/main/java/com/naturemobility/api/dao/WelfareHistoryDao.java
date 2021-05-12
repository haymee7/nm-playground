package com.naturemobility.api.dao;

import com.naturemobility.api.dto.welfare.WelfareHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WelfareHistoryDao {
    public abstract int save(WelfareHistoryDto welfareHistoryDto);
    public abstract WelfareHistoryDto findPayedByTnoAndOrderNo(@Param("tno") String tno, @Param("orderNo") String orderNo);
}
