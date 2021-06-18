package com.mot.service;

import com.mot.entity.UserEntity;
import com.mot.mapper.UserMapper;
import com.mot.model.ParamUserModel;
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
public class DemoService {

    @Resource
    UserMapper userMapper;

    @Autowired
    DemoService1 demoService1;

    @Transactional(rollbackFor = Exception.class)
    public void demoSelect() {
        ParamUserModel model = new ParamUserModel();
        model.setId("33");
        userMapper.queryUser(model);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void demoUpdate() {
        DefaultTransactionStatus transactionStatus = (DefaultTransactionStatus)TransactionAspectSupport.currentTransactionStatus();
        System.out.println("+++++++++++++++++++++++");
        System.out.println(transactionStatus.hashCode());
        System.out.println("+++++++++++++++++++++++");
        UserEntity entity = ceateDemoEntity("demoUser1");
        userMapper.insetUser(entity);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void demoUpdate1() {
        DefaultTransactionStatus transactionStatus = (DefaultTransactionStatus)TransactionAspectSupport.currentTransactionStatus();
        System.out.println("333333333333333333333333");
        System.out.println(transactionStatus.hashCode());
        System.out.println("333333333333333333333333");
        UserEntity entity = ceateDemoEntity("demoUser4");
        userMapper.insetUser(entity);
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
