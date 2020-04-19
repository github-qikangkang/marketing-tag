package com.iflytek.mapper;

import com.iflytek.domain.MemberChannel;
import com.iflytek.domain.MemberHeat;
import com.iflytek.domain.MemberMpSub;
import com.iflytek.domain.MemberSex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberEtlMapper {

    List<MemberSex> getMemberSex();

    List<MemberChannel> getMemberRegChannel();

    List<MemberMpSub> getMemberMpSub();

    MemberHeat getMemberHeat();
    Integer getCoupon();

    Integer getOrder();
    Integer getOrderAgain();
}
