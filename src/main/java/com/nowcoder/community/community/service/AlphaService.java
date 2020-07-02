package com.nowcoder.community.community.service;

import com.nowcoder.community.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println ( "实例华AlphaService" );
    }

    @PostConstruct   /*在调用构造方法以前先执行*/
    public void init(){
        System.out.println ( "初始化AlphaService" );
    }

    @PreDestroy  /*销毁对象之前调用*/
    public void destroy(){
        System.out.println ( "销毁对象前调用destroy方法" );
    }

    public String find(){
        return alphaDao.select ();

    }
}
