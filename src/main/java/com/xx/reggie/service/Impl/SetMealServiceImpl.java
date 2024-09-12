package com.xx.reggie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.reggie.common.CustomException;
import com.xx.reggie.dto.SetmealDto;
import com.xx.reggie.entity.Setmeal;
import com.xx.reggie.entity.SetmealDish;
import com.xx.reggie.mapper.SetMealMapper;
import com.xx.reggie.service.SetMealDishService;
import com.xx.reggie.service.SetMealService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {

    @Autowired
    private SetMealDishService setMealDishService;

    //新增套餐，同时保存套餐与菜品之间的关联信息
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐基本信息
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息
        setMealDishService.saveBatch(setmealDishes);
    }

    //删除套餐，同时需要删除套餐和菜品的关联数据
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态，看是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        int count = (int) this.count(queryWrapper);
        //如果不能删除，抛出一个业务异常
        if(count > 0){
            throw new CustomException("套餐正在售卖中，无法删除");
        }
        //如果可以删除，先删除套餐表中的数据
        this.removeByIds(ids);


        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //删除关系表中的数据
        setMealDishService.remove(lambdaQueryWrapper);
    }



    //修改套餐
    //根据id查询套餐信息及菜品关联信息
    @Transactional
    public SetmealDto getByIdWithDish(Long id) {
        //查询套餐信息
        Setmeal setmeal = this.getById(id);

        SetmealDto setmealDto = new SetmealDto();
        //查询当前套餐关联的菜品信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(id!=null,SetmealDish::getSetmealId,id);
        if(setmeal != null){
            BeanUtils.copyProperties(setmeal,setmealDto);
            List<SetmealDish> setmealDishes = setMealDishService.list(queryWrapper);
            setmealDto.setSetmealDishes(setmealDishes);
            return setmealDto;
        }
       return null;
    }


}
