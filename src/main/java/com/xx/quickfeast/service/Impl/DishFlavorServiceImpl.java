package com.xx.quickfeast.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.quickfeast.entity.DishFlavor;
import com.xx.quickfeast.mapper.DishFlavorMapper;
import com.xx.quickfeast.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
