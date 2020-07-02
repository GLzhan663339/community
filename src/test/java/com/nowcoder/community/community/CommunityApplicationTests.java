package com.nowcoder.community.community;

import com.nowcoder.community.community.dao.AlphaDao;
import com.nowcoder.community.community.dao.UserMapper;
import com.nowcoder.community.community.entity.User;
import com.nowcoder.community.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)  /*测试过程中希望和正式运行的CommunityApplication该引导类的
配置是一致的，所以在这通过注解给测试类导入正式运行类的配置*/
/*哪个类想得到容器，就哪个类实现ApplicationContextAware*/
public class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println ( applicationContext );
		AlphaDao alphaDao = applicationContext.getBean ( AlphaDao.class );  /*依赖的是接口*/
		System.out.println ( alphaDao.select () );
		AlphaDao alphaDao1 = applicationContext.getBean ( "alphaHibernate",AlphaDao.class ); /* 这个根据名字来获取bean对象，后一个参数是指定返回数据类型*/
		System.out.println ( alphaDao1.select () );
	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean ( AlphaService.class );
		System.out.println ( alphaService );
		alphaService = applicationContext.getBean ( AlphaService.class );
		System.out.println ( alphaService );
		/*说明bean对象默认情况在整个过程中只是实例化一次，如果需要实例化多次（多例）需要在类前添加注解@Scope(prototype)声明为多例的*/
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean ( SimpleDateFormat.class );
		System.out.println ( simpleDateFormat.format ( new Date (  ) ) );
	}

	///////依赖注入方式获取bean对象
	@Autowired
	private AlphaDao alphaDao;


	@Qualifier("alphaHibernate")/* 指定要哪个bean*/
	@Autowired
	private AlphaDao alphaDao1;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private  SimpleDateFormat simpleDateFormat;

	@Test
	public void testDi(){
		System.out.println ( alphaDao );
		System.out.println ( simpleDateFormat );
		System.out.println ( alphaDao1 );
		System.out.println ( alphaService );
	}

	@Test
	public void testServiceDao(){
		System.out.println ( alphaService.find ());
	}
	@Test
	public void testControler(){
		System.out.println ( alphaDao.select () );
	}

	@Autowired
	private UserMapper usermapper;

	@Test
	public void testSelectUser(){
		User user= usermapper.selectById ( 101 );
		System.out.println ( user );
	}
}
