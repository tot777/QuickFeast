package com.xx.quickfeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.quickfeast.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}