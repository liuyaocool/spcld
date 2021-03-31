package com.liuyao.spcld.seatarm;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeataRmDao {
    public Integer insertRm1(TbSeataRm tbSeataRm1);
    public Integer insertRm2(TbSeataRm tbSeataRm1);
}
