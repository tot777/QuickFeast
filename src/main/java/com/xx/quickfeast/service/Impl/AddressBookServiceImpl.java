package com.xx.quickfeast.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.quickfeast.entity.AddressBook;
import com.xx.quickfeast.mapper.AddressBookMapper;
import com.xx.quickfeast.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
