package com.nowcoder.community.community;

import com.nowcoder.community.community.dao.DiscussPostMapper;
import com.nowcoder.community.community.dao.UserMapper;
import com.nowcoder.community.community.entity.DiscussPost;
import com.nowcoder.community.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById ( 101 );
        System.out.println ( user );

        user= userMapper.selectByName ( "liubei" );
        System.out.println ( user );

        user = userMapper.selectByEmail ( "nowcoder101@sina.com" );
        System.out.println ( user );
    }

    @Test
    public void testInsertUser(){
        User user = new User ();
        user.setUsername ( "testname" );
        user.setCreateTime ( new Date (  ) );
        user.setActivationCode ( "df" );
        user.setEmail ( "zzzz@qq.com" );
        user.setHeaderUrl ( "ok" );
        user.setSalt ( "1" );
        user.setType ( 0 );
        int result = userMapper.insertUser ( user );
        System.out.println ( result );
        System.out.println ( user.getId () );
    }
    @Test
    public void testupdate(){
        int rows = userMapper.updateStatus ( 150,1 );
        System.out.println ( rows );

        rows= userMapper.updateHeader ( 150,"www.zgl.com" );
        System.out.println ( rows );

        rows = userMapper.updatePassword ( 150,"66666" );
        System.out.println ( rows );

    }
    @Autowired
    private DiscussPostMapper discusspostmapper;

    @Test
    public void testSelectPosts(){
        List<DiscussPost> discussPosts = discusspostmapper.selectDiscussPosts ( 0, 0, 10 );
        for (DiscussPost post:discussPosts){
            System.out.println ( post );
        }
    }
    @Test
    public void TestspringVersionAndspringBootVersion (){
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println ( springBootVersion );
        System.out.println ( springVersion );
    }



}
