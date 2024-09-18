package com.xx.quickfeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.quickfeast.dto.SetmealDto;
import com.xx.quickfeast.entity.Setmeal;

import java.util.List;

public interface SetMealService extends IService<Setmeal> {
    //新增套餐，同时保存套餐与菜品之间的关联信息
    public void saveWithDish(SetmealDto setmealDto);

    //删除套餐，同时需要删除套餐和菜品的关联数据
    public void removeWithDish(List<Long> ids);

    //根据id查询套餐信息及菜品关联信息
    public SetmealDto getByIdWithDish(Long id);

}
