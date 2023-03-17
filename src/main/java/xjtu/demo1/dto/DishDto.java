package xjtu.demo1.dto;


import lombok.Data;
import xjtu.demo1.entity.Dish;
import xjtu.demo1.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}