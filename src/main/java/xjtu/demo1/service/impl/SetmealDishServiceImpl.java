package xjtu.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xjtu.demo1.entity.SetmealDish;
import xjtu.demo1.mapper.SetmealDishMapper;
import xjtu.demo1.service.SetmealDishService;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
