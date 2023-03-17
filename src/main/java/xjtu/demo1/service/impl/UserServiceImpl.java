package xjtu.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xjtu.demo1.entity.User;
import xjtu.demo1.mapper.UserMapper;
import xjtu.demo1.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
