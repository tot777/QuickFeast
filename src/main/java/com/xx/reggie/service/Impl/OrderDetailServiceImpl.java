package com.xx.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.reggie.entity.OrderDetail;
import com.xx.reggie.mapper.OrderDetailMapper;
import com.xx.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}