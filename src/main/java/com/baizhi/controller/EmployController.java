package com.baizhi.controller;

import com.baizhi.entity.Employ;
import com.baizhi.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author : 张京斗
 * create_date : 2019/12/6 17:50
 * version : 1.0
 */
@Controller
@RequestMapping("Emp")
public class EmployController {
    @Autowired
    private EmployService employService;

    @ResponseBody   //员工的分页查询
    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(String deptid, Integer rows, Integer page) {
        Map map = new HashMap();
        Integer listcount = employService.getCount();
        //计算最大页数
        Integer totalPage = (listcount % rows == 0) ? listcount / rows : listcount / rows + 1;
        //获取该页的内容
        List<Employ> byPage = employService.findByPage(page, rows, deptid);
        //放当前页数
        map.put("page", page);
        //放入最大页数
        map.put("total", totalPage);
        //放入数据条数
        map.put("records", listcount);
        //放入该页的数据
        map.put("rows", byPage);
        return map;
    }

    //添加，修改，删除
    @RequestMapping("control")
    @ResponseBody
    public Map<String, Object> deleteOne(String deptid, String oper, Employ employ, HttpServletRequest request) {
        HashMap hashMap = new HashMap();
        if ("add".equals(oper)) {
            employ.setId(UUID.randomUUID().toString());
            employ.setDepeid(deptid);
            hashMap.put("theAdd", employ);
            return hashMap;
        }
        if ("edit".equals(oper)) {
            employ.setDepeid(deptid);
            employService.updateOne(employ);
            hashMap.put("theUpdate", employ);
            return hashMap;
        }
        if ("del".equals(oper)) {
            employService.deleteOne(employ);
            hashMap.put("theDelete", employ);
            return hashMap;
        }
        return null;
    }
}
