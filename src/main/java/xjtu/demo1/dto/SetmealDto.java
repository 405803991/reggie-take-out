package xjtu.demo1.dto;


import lombok.Data;
import xjtu.demo1.entity.Setmeal;
import xjtu.demo1.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
