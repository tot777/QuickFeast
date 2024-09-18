package com.xx.quickfeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.quickfeast.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
