package com.nowcoder.community.community.controller;

import com.nowcoder.community.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String SayHello(){
        return "hello!";
    }

    @RequestMapping("/date")
    @ResponseBody
    public String getData(){
        return alphaService.find ();
    }
    @RequestMapping("http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println ( request.getMethod () );
        System.out.println ( request.getServletPath () );
        Enumeration<String> enumeration = request.getHeaderNames ();
        while(enumeration.hasMoreElements ()){
            String name = enumeration.nextElement ();
            String  vale = request.getHeader ( name );
            System.out.println ( name +":"+vale );
        }
        System.out.println ( request.getParameter ( "code" ) );

        //返回响应数据
        response.setContentType ( "text/html;charset=utf-8" );
        try (
                PrintWriter writer = response.getWriter ( );
                ){
            writer.write ( "<h1>牛客网</h1>" );
            writer.write ( "dfadf" );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    //GET请求方式
    //studetn?current=1&limit=20//student当前第1页，每页数据20条
    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false, defaultValue = "20") int limit){
        System.out.println ( "current" +current);
        System.out.println ( "limit:"+limit );
        return "some students";
    }

    @RequestMapping(path = "/student2/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println ( id );
        return "a student";
    }

    @RequestMapping(path= "/student3", method = RequestMethod.POST)
    @ResponseBody
    public String savestudent(String name,int age){
        System.out.println ( name );
        System.out.println ( age );
        return  "success";
    }
    //-------------------------以上是网页提交数据，服务器接收数据------------
    //-------------------------以下是服务器有数据，发送给网页----------------
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        //将数据封装到Model中
        ModelAndView modelandview = new ModelAndView (  );
        modelandview.addObject ( "name","王五" );
        modelandview.addObject ( "age",20 );
        //给打包好的数据设置模板所在的路径，最终送到哪里去
        modelandview.setViewName ( "demo/view" ); /*/路径/文件名;文件名不能带后缀.html，因为
        内部已经自动附上，否则访问不到该文件报500错误；如果没有写对路径名，直接是拒绝网页访问*/
        return modelandview;
    }
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model mdel){
        mdel.addAttribute ( "name","北京大学" );
        mdel.addAttribute ( "age",90 );
        return "/demo/view";
    }

    //----------------------浏览器响应json数据------------------
    //异步请求的时候（整个网页不刷新，但访问了服务器，返回是否判断），注册时，输入昵称光标移动后，马上有显示
    //java对象 ----> json字符窜 ---->  js对象/C#等等等
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object>map = new HashMap<> (  );
        map.put ( "name","张三" );
        map.put ( "age",20 );
        map.put ( "salary",8700.00 );
        return map;
    }
    /*返回的是对象，内部自动给对象转成json对象：{key1：value1，key2：value2...}*/

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String ,Object>> list = new ArrayList<> (  );
        Map<String , Object>map = new HashMap<> (  );
        map.put ( "name1","李狗" );
        map.put ( "name2:","Jonsn" );
        map.put ( "age",36 );
        map.put ( "salary",63300 );
        list.add ( map );

        Map<String , Object>map1 = new HashMap<> (  );
        map1.put ( "name1","李daye" );
        map1.put ( "name2:","Jonsnf" );
        map1.put ( "age",16 );
        map1.put ( "salary",600 );
        list.add ( map1 );

        Map<String , Object>map3 = new HashMap<> (  );
        map3.put ( "name1","李狗2" );
        map3.put ( "name2:","Jonsn2" );
        map3.put ( "age",36 );
        map3.put ( "salary",400 );
        list.add ( map3 );

        Map<String , Object>map4 = new HashMap<> (  );
        map4.put ( "name1","wang" );
        map4.put ( "name2:","Jon" );
        map4.put ( "age",16 );
        map4.put ( "salary",6300 );
        list.add ( map4 );

        return list;
    }
}
