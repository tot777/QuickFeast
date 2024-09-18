package com.xx.quickfeast.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.quickfeast.entity.OrderDetail;
import com.xx.quickfeast.mapper.OrderDetailMapper;
import com.xx.quickfeast.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}