package com.xx.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xx.reggie.common.BaseContext;
import com.xx.reggie.common.R;
import com.xx.reggie.entity.ShoppingCart;
import com.xx.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    //添加菜品到购物车
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        //设置用户id，指定当前是那个用户登录
        Long CurrentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(CurrentId);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,CurrentId);

        //添加的是菜品
        if (dishId != null){
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }
        //添加的是套餐
        else {
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //查询当前菜品或套餐是否在购物车中
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        //如果在，则在原来的数量上加一
        if(cartServiceOne != null){
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        }
        //如果不在，则添加至购物车中，数量默认为一
        else{
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }
        return R.success(cartServiceOne);
    }


    //减少购物车中选取的数量
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        //设置用户id，指定当前是那个用户登录
        Long CurrentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(CurrentId);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,CurrentId);

        //添加的是菜品
        if (dishId != null){
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }
        //添加的是套餐
        else {
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //查询当前菜品或套餐是否在购物车中
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        //如果在，则在原来的数量上减一
        if(cartServiceOne != null){
            Integer number = cartServiceOne.getNumber();
            if(number > 1){
                cartServiceOne.setNumber(number - 1);
                shoppingCartService.updateById(cartServiceOne);
            } else {
                shoppingCartService.remove(queryWrapper);
            }
        }
        return R.success(cartServiceOne);
    }

    //查看购物车
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        queryWrapper.orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return R.success(list);
    }

    //清空购物车
    @DeleteMapping("/clean")
    public R<String> clean(){
       LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
       queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());

       shoppingCartService.remove(queryWrapper);

        return R.success("成功清空购物车");

    }
}
