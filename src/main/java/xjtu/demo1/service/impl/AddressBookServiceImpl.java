package xjtu.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xjtu.demo1.entity.AddressBook;
import xjtu.demo1.mapper.AddressBookMapper;
import xjtu.demo1.service.AddressBookService;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
