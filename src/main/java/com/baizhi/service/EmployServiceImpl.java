package com.baizhi.service;

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

    @Override
    public List<Employ> findByPage(Integer nowpage, Integer pagesize, String deptid) {
        Employ employ = new Employ();
        employ.setDeptid(deptid);
        Integer thebegin = (nowpage - 1) * pagesize;
        return employDao.selectByRowBounds(employ, new RowBounds(thebegin, pagesize));
    }

    @Override
    public void addOne(Employ employ) {
        employDao.insert(employ);
    }

    @Override
    public void updateOne(Employ employ) {
        employDao.updateByPrimaryKeySelective(employ);
    }

    @Override
    public void deleteOne(Employ employ) {
        employDao.delete(employ);
    }

    @Override
    public Integer getCount() {
        return employDao.selectCount(new Employ());
    }


}
