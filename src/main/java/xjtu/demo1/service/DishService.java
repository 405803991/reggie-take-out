package xjtu.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xjtu.demo1.dto.DishDto;
import xjtu.demo1.entity.Dish;

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
