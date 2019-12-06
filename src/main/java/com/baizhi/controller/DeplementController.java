package com.baizhi.controller;

import com.baizhi.entity.Deplement;
import com.baizhi.entity.Employ;
import com.baizhi.service.DeplementService;
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
 * create_date : 2019/12/6 17:29
 * version : 1.0
 */
@Controller
@RequestMapping("Dept")
public class DeplementController {
    @Autowired
    private DeplementService deplementService;
    @Autowired
    private EmployService employService;

    @ResponseBody   //部门的分页查询
    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(Integer rows, Integer page) {
        Map map = new HashMap();
        Integer listcount = deplementService.getCount();
        //计算最大页数
        Integer totalPage = (listcount % rows == 0) ? listcount / rows : listcount / rows + 1;
        //获取该页的内容
        List<Deplement> byPage = deplementService.findByPage(page, rows);
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
    public Map<String, Object> deleteOne(String oper, Deplement deplement, HttpServletRequest request) {
        HashMap hashMap = new HashMap();
        if ("add".equals(oper)) {
            deplement.setId(UUID.randomUUID().toString());
            deplement.setCount(0);
            deplementService.addOne(deplement);
            hashMap.put("theAdd", deplement);
            return hashMap;
        }
        if ("edit".equals(oper)) {
            deplementService.updateOne(deplement);
            hashMap.put("theUpdate", deplement);
            return hashMap;
        }
        if ("del".equals(oper)) {
            deplementService.deleteOne(deplement.getId());
            Employ employ = new Employ();
            employ.setDeptid(deplement.getId());
            employService.deleteOne(employ);
            hashMap.put("theDelete", deplement);
            return hashMap;
        }
        return null;
    }


}
