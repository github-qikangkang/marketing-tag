package com.iflytek.mapper;

import com.iflytek.domain.Order;
import com.iflytek.domain.Reg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

//环比指标
@Mapper
public interface WowEtlMapper {

    List<Reg> regCountNum();

}
