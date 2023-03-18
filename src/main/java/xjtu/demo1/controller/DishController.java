package xjtu.demo1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;
import xjtu.demo1.common.R;
import xjtu.demo1.dto.DishDto;
import xjtu.demo1.entity.Category;
import xjtu.demo1.entity.Dish;
import xjtu.demo1.entity.DishFlavor;
import xjtu.demo1.service.CategoryService;
import xjtu.demo1.service.DishFlavorService;
import xjtu.demo1.service.DishService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    private R<String> save(@RequestBody DishDto dishDto) {

        dishService.saveWithFlavor(dishDto);
        return R.success("新增成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(name != null, Dish::getName, name);

        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);

        dishService.page(pageInfo, lambdaQueryWrapper);

        BeanUtils.copyProperties(pageInfo, dishDtoPage);

        List<Dish> records = pageInfo.getRecords();

        List<DishDto> list = records.stream().map((item) -> {

            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();

            Category category = categoryService.getById(categoryId);

            String categoryName = category.getName();

            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);
    }

    @GetMapping("/{id}")
    public R<DishDto> queryDishInfo(@PathVariable Long id) {

        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return R.success(dishDto);

    }

    @PutMapping
    private R<String> update(@RequestBody DishDto dishDto) {


        // 清理所有缓存
        //Set keys = redisTemplate.keys("dish_*");
        //redisTemplate.delete(keys);

        // 清理某个缓存
        String key = "dish_" + dishDto.getCategoryId() + "_1";
        redisTemplate.delete(key);


        dishService.updateWithFlavor(dishDto);
        return R.success("新增成功");
    }

/*    @GetMapping("/list")
    public R<List<Dish>> list(Dish dish) {

        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        lambdaQueryWrapper.eq(Dish::getStatus, 1);

        lambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(lambdaQueryWrapper);

        return R.success(list);
    }*/

    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish) {
        List<DishDto> listDto = null;
        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();
        // 从redis中获取缓存数据
        listDto = (List<DishDto>) redisTemplate.opsForValue().get(key);

        if (listDto != null) {
            return R.success(listDto);
        }


        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        lambdaQueryWrapper.eq(Dish::getStatus, 1);
        lambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(lambdaQueryWrapper);

        listDto = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if(category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            Long dishId = item.getId();

            LambdaQueryWrapper<DishFlavor> qw = new LambdaQueryWrapper<>();
            qw.eq(DishFlavor::getDishId, dishId);

            List<DishFlavor> list1 = dishFlavorService.list(qw);
            dishDto.setFlavors(list1);
            return dishDto;
        }).collect(Collectors.toList());

        // 不存在redis则查询数据库存入redis
        redisTemplate.opsForValue().set(key, listDto, 1, TimeUnit.HOURS);

        return R.success(listDto);
    }

}
