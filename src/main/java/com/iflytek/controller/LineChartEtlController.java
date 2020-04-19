package com.iflytek.controller;

import com.iflytek.domain.LineVo;
import com.iflytek.mapper.LineChartEtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
//折线指标相关
@Controller
public class LineChartEtlController {
    @Autowired
    private LineChartEtlMapper lineChartEtlMapper;
    @RequestMapping("/lineVos")
    @ResponseBody
    public List<LineVo> lineVos(){
        //regCount,memberCount
        List<LineVo> lineVos = lineChartEtlMapper.getRegCount();
        return lineVos;
    }
}
