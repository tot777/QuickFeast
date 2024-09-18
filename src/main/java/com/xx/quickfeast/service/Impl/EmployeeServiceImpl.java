package com.xx.quickfeast.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.quickfeast.entity.Employee;
import com.xx.quickfeast.mapper.EmployeeMapper;
import com.xx.quickfeast.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
