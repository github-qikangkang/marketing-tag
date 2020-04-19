package com.iflytek.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.domain.es.EsTag;

import com.iflytek.domain.es.MeMberTagEs;
import com.iflytek.mapper.es.EsMappingEtlMapper;
import com.iflytek.service.EsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
@Controller
public class EsMappingEtlController {
    @Autowired
    private EsQueryService esQueryService;

    @Autowired
    private EsMappingEtlMapper esMappingEtlMapper;


    @RequestMapping("/gen")
    public void genAndDown(HttpServletResponse response, @RequestBody String data) throws IOException {
        JSONObject object = JSON.parseObject(data);
        JSONArray selectedTags = object.getJSONArray("selectedTags");
        List<EsTag> list = selectedTags.toJavaList(EsTag.class);

        System.out.println("list================"+list.toString());
        List<MeMberTagEs> tags = esQueryService.buildQuery(list);
        String content = toContent(tags);
        String fileName = "member.txt";
        response.setContentType("application/octet-stream");

        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ServletOutputStream sos = null;
        BufferedOutputStream bos = null;

        try {
            sos = response.getOutputStream();
            bos = new BufferedOutputStream(sos);
            bos.write(content.getBytes("UTF-8"));
            bos.flush();
            bos.close();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @RequestMapping("/down")
    public void down(HttpServletResponse response) {
        String fileName = "member.txt";
        response.setContentType("text/plain");
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ServletOutputStream sos = null;
        BufferedOutputStream bos = null;

        try {
            sos = response.getOutputStream();
            bos = new BufferedOutputStream(sos);
            bos.write("content".getBytes("UTF-8"));
            bos.flush();
            bos.close();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String toContent(List<MeMberTagEs> tags) {
        StringBuilder sb = new StringBuilder();
        for (MeMberTagEs tag : tags) {
            sb.append("[" + tag.getMemberId() + "," + tag.getPhone() + "]\r\n");
        }

        return sb.toString();

    }
}
