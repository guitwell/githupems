package com.baizhi.service;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.DelCache;
import com.baizhi.dao.EmployDao;
import com.baizhi.entity.Employ;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author : 张京斗
 * create_date : 2019/12/6 11:32
 * version : 1.0
 */
@Service
@Transactional
public class EmployServiceImpl implements EmployService {
    @Autowired
    private EmployDao employDao;

    @AddCache("查询一个部门所有员工")
    @Override
    public List<Employ> findByPage(Integer nowpage, Integer pagesize, String deptid) {
        Employ employ = new Employ();
        employ.setDeptid(deptid);
        Integer thebegin = (nowpage - 1) * pagesize;
        return employDao.selectByRowBounds(employ, new RowBounds(thebegin, pagesize));
    }

    @DelCache("添加一个员工")
    @Override
    public void addOne(Employ employ) {
        employDao.insert(employ);
    }

    @DelCache("更新一个员工")
    @Override
    public void updateOne(Employ employ) {
        employDao.updateByPrimaryKeySelective(employ);
    }

    @DelCache("删除一个员工")
    @Override
    public void deleteOne(Employ employ) {
        employDao.delete(employ);
    }

    @AddCache("查询某一部门下员工数量")
    @Override
    public Integer getCount() {
        return employDao.selectCount(new Employ());
    }


}
