package xjtu.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xjtu.demo1.common.CustomException;
import xjtu.demo1.entity.Category;
import xjtu.demo1.entity.Dish;
import xjtu.demo1.entity.Setmeal;
import xjtu.demo1.mapper.CategoryMapper;
import xjtu.demo1.service.CategoryService;
import xjtu.demo1.service.DishService;
import xjtu.demo1.service.SetmealService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private DishService dishService;

    @Override
    public void remove(long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<Dish>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("删除失败");
        }
        LambdaQueryWrapper<Setmeal> SetmealLambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>();
        SetmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = dishService.count();
        if (count2 > 0) {
            throw new CustomException("删除失败");
        }
        super.removeById(id);
    }
}
