package com.xx.quickfeast.dto;

import com.xx.quickfeast.entity.Setmeal;
import com.xx.quickfeast.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
