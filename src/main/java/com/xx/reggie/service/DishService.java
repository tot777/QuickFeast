package com.xx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.reggie.dto.DishDto;
import com.xx.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时保存对应的口味数据
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);
}
