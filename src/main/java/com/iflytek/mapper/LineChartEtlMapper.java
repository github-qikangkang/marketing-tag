package com.iflytek.mapper;

import com.iflytek.domain.LineVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LineChartEtlMapper {

    List<LineVo> getRegCount();
}
