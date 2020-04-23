package com.iflytek.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.domain.CosEntity;
import com.iflytek.domain.UserEntity;
import com.iflytek.domain.es.EsTag;

import com.iflytek.domain.es.EsTagResult;
import com.iflytek.domain.es.MeMberTagEs;
import com.iflytek.mapper.es.EsMappingEtlMapper;
import com.iflytek.service.EsQueryService;
import com.iflytek.utils.RecommendCos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/genForeCast")
    @ResponseBody
    public List<EsTagResult> genForeCast(HttpServletResponse response, @RequestBody String data) throws IOException {
        JSONObject obj = JSON.parseObject(data);
        JSONArray tagsId = obj.getJSONArray("selectedTags");
        List<EsTag> list = tagsId.toJavaList(EsTag.class);
        System.out.println(list.toString());
        String item = null;
        for (EsTag tag :
                list) {
            if (tag.getName().equals("favGoods")){
                item=tag.getValue();
                System.out.println(item);
            }else {
                continue;
            }
        }
        List<MeMberTagEs> tags = esQueryService.buildQuery(list);
        //返回结果进行预测
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity;
        for (MeMberTagEs tagEs:
             tags) {
            if (tagEs.getFavGoods()==null){
                continue;
            }
            userEntity = new UserEntity();
            userEntity.setId(tagEs.getMemberId());
            userEntity.setPhone(tagEs.getPhone());
            String str="";
            for (Integer food:tagEs.getFavGoods()){
                str+=String.valueOf(food)+"\t";
            }
            userEntity.setVFoods(str);
            userEntities.add(userEntity);
        }
        CosEntity cosEntity = RecommendCos.itemUser(item, userEntities);
        List<EsTagResult> esTagResults = new ArrayList<>();
        EsTagResult esTagResult = null;
        String esStr = null;
        if (cosEntity.getItemId()==1){
            esStr="卡布奇诺10元优惠卷一张";
        }else if (cosEntity.getItemId()==2){
            esStr="美式咖啡10元优惠卷一张";
        }else if (cosEntity.getItemId()==3){
            esStr="拿铁10元优惠卷一张";
        }else if (cosEntity.getItemId()==4){
            esStr="摩卡10元优惠卷一张";
        }else if (cosEntity.getItemId()==5){
            esStr="橙汁10元优惠卷一张";
        }else if (cosEntity.getItemId()==6){
            esStr="石榴汁10元优惠卷一张";
        }else if (cosEntity.getItemId()==7){
            esStr="西瓜汁10元优惠卷一张";
        }else if (cosEntity.getItemId()==8){
            esStr="芒果汁10元优惠卷一张";
        }else if (cosEntity.getItemId()==9){
            esStr="猕猴桃汁10元优惠卷一张";
        }else if (cosEntity.getItemId()==10){
            esStr="沙琪玛10元优惠卷一张";
        }else if (cosEntity.getItemId()==11){
            esStr="面包干10元优惠卷一张";
        }else if (cosEntity.getItemId()==12){
            esStr="酸奶10元优惠卷一张";
        }else if (cosEntity.getItemId()==13){
            esStr="火腿10元优惠卷一张";
        }else if (cosEntity.getItemId()==14){
            esStr="汉堡10元优惠卷一张";
        }else if (cosEntity.getItemId()==15){
            esStr="咖啡杯10元优惠卷一张";
        }else if (cosEntity.getItemId()==16){
            esStr="咖啡机10元优惠卷一张";
        }else if (cosEntity.getItemId()==17){
            esStr="果汁机10元优惠卷一张";
        }else if (cosEntity.getItemId()==18){
            esStr="咖啡物语10元优惠卷一张";
        }else if (cosEntity.getItemId()==19){
            esStr="钥匙扣10元优惠卷一张";
        }
        for (MeMberTagEs esTag:tags) {
            esTagResult = new EsTagResult();
            esTagResult.setUserId(esTag.getMemberId());
            esTagResult.setPhone(esTag.getPhone());
            esTagResult.setResult(esStr);
            esTagResults.add(esTagResult);
        }
        System.out.println(esTagResults.toString());
        return esTagResults;
    }

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
            sb.append("" + tag.getMemberId() + "\t" + tag.getPhone() + "\t" + tag.getFavGoods() + "\r\n");
        }
        return sb.toString();

    }
}
