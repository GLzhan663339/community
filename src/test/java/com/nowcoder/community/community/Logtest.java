package com.nowcoder.community.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class Logtest {

    //1.在配置文件中配置loggger日志类的配置
    //2.实例化一个logger类对象
    private static final Logger loggger = LoggerFactory.getLogger ( Logtest.class );

    @Test
    public void testLogger(){
        System.out.println ( loggger.getName () );
        //3.调用logger对象的方法
        loggger.debug ( "debug log" );
        loggger.info ( "info log" );
        loggger.warn ( "warn log" );
        loggger.error ( "error log" );


    }
}
