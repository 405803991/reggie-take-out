package xjtu.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xjtu.demo1.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
