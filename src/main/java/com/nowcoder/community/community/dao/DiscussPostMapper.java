package com.nowcoder.community.community.dao;

import com.nowcoder.community.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface DiscussPostMapper {
    /*在此我们做的功能是分页查询，查询的结果应该是有多条数据，所以返回的应该是一个集合，集合里面装的是对象*/
    List<DiscussPost> selectDiscussPosts(int userId,int offset, int limit);//考虑到个人用户采用这个参数userId;
    //每页的起始行号；每一页存放多少条数据
    int selectDiscussPostRows(@Param ("userId") int userId); //在sql里动态条件if中用到该参数，且只有一个参数的时候，
    // 必须使用注解@Param给参数取别名，否则报错
}
