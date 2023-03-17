package xjtu.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xjtu.demo1.entity.ShoppingCart;
import xjtu.demo1.mapper.ShoppingCartMapper;
import xjtu.demo1.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
