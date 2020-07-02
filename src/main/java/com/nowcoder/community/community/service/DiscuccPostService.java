package com.nowcoder.community.community.service;

import com.nowcoder.community.community.dao.DiscussPostMapper;
import com.nowcoder.community.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscuccPostService {
    @Autowired
    private DiscussPostMapper dismapper;

    /*这层还要处理些内容：查询结果有userid这个外键，在显示界面的时候并不是显示外键，而是显示对应名字
    所以这里的处理方式是：在查询得到的每个DiscussPost ， 再单独用userId查询，将查到的名字和DiscussPost组合一起
    一起返回
    * */
    public List<DiscussPost> findDiscussPost(int userId,int offset, int limit){
        return dismapper.selectDiscussPosts ( userId,offset,limit );
    }

    public int findDiscussPostRows(int userId){
        return dismapper.selectDiscussPostRows ( userId );
    }
}
