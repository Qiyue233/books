package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Borrowing;
import com.example.books.bean.Msg;
import com.example.books.mapper.BorrowingMapper;
import com.example.books.service.BorrowingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BorrowingServiceImpl extends ServiceImpl<BorrowingMapper, Borrowing> implements BorrowingService {

    @Resource
    BorrowingMapper borrowingMapper;
    @Override
    public String getBookNameById(int id) {
        String name=borrowingMapper.selectNameById(id);
        return name;

    }

    @Override
    public String getTelNumber(String userName) {
        String telNumber=borrowingMapper.getTelNumber(userName);
        return telNumber;
    }
    //判断书籍是否借出
    @Override
    public Msg selectById(int[] id) {
        String flag="false";
        for (int i=0;i<id.length;i++){
            flag=borrowingMapper.selectInfoById(id[i]);
            if (flag!=null){
                return Msg.success().add("info",true);
            }
        }
        return Msg.success().add("info",false);
    }
    //更新书籍状态
    @Override
    public Msg updateStateByBorrowId(int[] id,String state) {
        for (int i=0;i<id.length;i++){
            borrowingMapper.updateStateByBorrowId(id[i],state);
        }
        return null;
    }
    @Override
    public Msg borrowById(int[] id, String userName) {
        String telNumber=getTelNumber(userName);

        //预计归还时间
        Date predictDate=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(predictDate);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 7);
        // 这个时间就是日期往后推一天的结果
        predictDate = calendar.getTime();


        java.util.Date date = new java.util.Date();
        SimpleDateFormat queueDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String out_date = queueDateFormat.format(date);
        String estimatedDate = queueDateFormat.format(predictDate);

        for (int i=0;i<id.length;i++){
            String name=getBookNameById(id[i]);
            borrowingMapper.borrowing(id[i],name,userName,telNumber,out_date,estimatedDate);
        }
        return Msg.success();
    }

    @Override
    public Msg getAllRecords(int pageNum) {
        PageHelper.startPage(pageNum,1);
        List<Borrowing> list= this.list();
        //将查询到的数据封装到PageInfo
        PageInfo<Borrowing> pageInfo=new PageInfo<>(list);
        return Msg.success().add("record",pageInfo);
    }


}
