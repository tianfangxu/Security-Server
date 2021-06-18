package com.mot.service;

import com.mot.entity.UserEntity;
import com.mot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.annotation.Resource;

/**
 * @author tianfx
 * @date 2021/5/13 10:01 上午
 */
@Service
public class DemoService1 {

    @Resource
    UserMapper userMapper;

    @Autowired
    DemoService demoService;

    @Transactional(rollbackFor = Exception.class)
    public void demoUpdate1() {
        DefaultTransactionStatus transactionStatus = (DefaultTransactionStatus)TransactionAspectSupport.currentTransactionStatus();
        System.out.println("111111111111111111111111");
        System.out.println(transactionStatus.hashCode());
        System.out.println("111111111111111111111111");
        UserEntity entity = ceateDemoEntity("demoUser2");
        userMapper.insetUser(entity);
    }


    public void demoUpdate2() {
        DefaultTransactionStatus transactionStatus = (DefaultTransactionStatus)TransactionAspectSupport.currentTransactionStatus();
        System.out.println("2222222222222222222222222222");
        System.out.println(transactionStatus.hashCode());
        System.out.println("2222222222222222222222222222");
        UserEntity entity = ceateDemoEntity("demoUser3");
        userMapper.insetUser(entity);
        demoService.demoUpdate1();
    }

    public static UserEntity ceateDemoEntity(String name){
        UserEntity entity = new UserEntity();
        entity.createEntity("1");
        entity.setPassword("1234");
        entity.setAvatar("123");
        entity.setEmail("1234D");
        entity.setRealName("111");
        entity.setUsername(name);
        entity.setPhone("1234567");
        return entity;
    }
}
