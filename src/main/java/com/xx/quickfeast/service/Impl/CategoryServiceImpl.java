package com.xx.quickfeast.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.quickfeast.common.CustomException;
import com.xx.quickfeast.entity.Category;
import com.xx.quickfeast.entity.Dish;
import com.xx.quickfeast.entity.Setmeal;
import com.xx.quickfeast.mapper.CategoryMapper;
import com.xx.quickfeast.service.CategoryService;
import com.xx.quickfeast.service.DishService;
import com.xx.quickfeast.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
   @Autowired
    private DishService dishService;

   @Autowired
   private SetMealService setMealService;


   //根据id删除分类，在删除前进行判断
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品，如果关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = (int)dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            //如果关联，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，无法删除");
        }

        //查询当前分类是否关联了套餐，如果关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = (int) setMealService.count(setmealLambdaQueryWrapper);
        if(count2>0){
            //如果关联，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，无法删除");
        }

        //正常删除id
        super.removeById(id);
    }
}
