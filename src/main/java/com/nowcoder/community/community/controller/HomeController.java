package com.nowcoder.community.community.controller;

import com.nowcoder.community.community.entity.DiscussPost;
import com.nowcoder.community.community.entity.Page;
import com.nowcoder.community.community.entity.User;
import com.nowcoder.community.community.service.DiscuccPostService;
import com.nowcoder.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscuccPostService discuccPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getInndexPage(Model model, Page page){
        //方法调用前，SpringMVC会自动实例化Model和Page，并将Page注入Model中，所以在thymeleaf中可以直接访问Page对象的数据，
        //这个是隐含的逻辑
        page.setRows ( discuccPostService.findDiscussPostRows ( 0 ) );
        page.setPath ( "/index" );
        List<DiscussPost> list = discuccPostService.findDiscussPost ( 0,page.getoffset (),page.getLimit () );

        //遍历取出list中的userId去User中查出对应的数据条，然后将这两部分的数据组合起来
        List<Map<String, Object>> discussPosts = new ArrayList<> (  );

        if (list!=null){
            for (DiscussPost post:list){
                Map<String, Object> map = new HashMap<> (  );
                map.put ( "post",post );
                User user = userService.findUserById ( post.getUserid () );
                map.put ( "user",user );
                discussPosts.add ( map );
            }
        }
        model.addAttribute ( "discussPosts",discussPosts );
        return "/index";
    }
}
