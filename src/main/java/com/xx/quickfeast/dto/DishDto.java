package com.xx.quickfeast.dto;

import com.xx.quickfeast.entity.Dish;
import com.xx.quickfeast.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
