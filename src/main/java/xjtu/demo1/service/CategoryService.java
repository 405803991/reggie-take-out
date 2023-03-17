package xjtu.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xjtu.demo1.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(long id);
}
