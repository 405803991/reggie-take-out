package xjtu.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xjtu.demo1.entity.Dish;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
