package xjtu.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xjtu.demo1.entity.Category;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
