package xjtu.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xjtu.demo1.dto.SetmealDto;
import xjtu.demo1.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
