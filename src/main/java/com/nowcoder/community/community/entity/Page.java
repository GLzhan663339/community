package com.nowcoder.community.community.entity;
/*
封装分页相关的信息
调用的时候只需要外界给两个赋值就ok，一个参数：当前页面的路径path
一个数据的总行数
*/
public class Page {
    //当前页码
    private  int  current = 1;

    //每页显示的上限
    private  int limit = 10;

    //需要外面传入数据进行初始化
    //数据总数（用于计算总页数）
    private int rows;
    //查询路径（用于复用分页链接）
    private String path;




    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >=1){this.current = current;}

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit>=1&&limit<=100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows>=0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    //补充几个方法

    /**作用：获取当前页的数据在数据库的表中的位置
     * 数据库查询的时候是需要输入一个起始行，并不需要当前页码，所以在此写一个方法得到当前页的起始行
     * @return
     */
    public int getoffset(){
        //current * limit -limit;//当前第几页*每页有多少条数据-当前页的数据，也就是当前页数据在数据库里的位置
        return (current-1) *limit;
    }

    /**
     * 获取总的数据，作用：用于计算页面上显示总页数
     * @return
     */
    public int getTotal(){
        //rows/limit[+1]
        if(rows % limit ==0){
            return rows/limit;
        }else {
            return rows/limit +1;
        }
    }

    /**
     * 获取起始页码
     * @return
     */
    public int getform(){
        int from = current-2;
        return from<1?1:from;
    }

    /**
     * 获取结束页码
     * @return
     */
    public int getto(){
        int to = current +2;
        int total = getTotal ();
        return current>total? total: current;
    }
}
