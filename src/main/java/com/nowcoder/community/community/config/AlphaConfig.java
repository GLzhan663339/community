package com.nowcoder.community.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration  /*这个注解 表示：该类为一个配置类文件*/
public class AlphaConfig {


    @Bean
    public SimpleDateFormat impleDateFormat(){
        return  new SimpleDateFormat ( "yyy-MM-dd HH:mm:ss" );
    }
}
