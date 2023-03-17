package xjtu.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xjtu.demo1.entity.Employee;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
