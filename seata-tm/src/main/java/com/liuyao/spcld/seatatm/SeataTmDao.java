package com.liuyao.spcld.seatatm;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeataTmDao {
    public String insertTm(TbSeataTm tbSeataTm);
}
