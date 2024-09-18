package com.xx.quickfeast.dto;

import com.xx.quickfeast.entity.OrderDetail;
import com.xx.quickfeast.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}