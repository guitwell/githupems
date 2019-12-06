package com.baizhi.service;

import com.baizhi.dao.DeplementDao;
import com.baizhi.entity.Deplement;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author : 张京斗
 * create_date : 2019/12/6 16:32
 * version : 1.0
 */
@Service
@Transactional
public class DeplementServiceImpl implements DeplementService {
    @Autowired
    private DeplementDao deplementDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Deplement> findByPage(Integer nowpage, Integer pagesize) {
        Deplement deplement = new Deplement();
        Integer thebegin = (nowpage - 1) * pagesize;
        return deplementDao.selectByRowBounds(deplement, new RowBounds(thebegin, pagesize));
    }

    @Override
    public Deplement findOne(String deptid) {
        return deplementDao.selectByPrimaryKey(deptid);
    }

    @Override
    public void addOne(Deplement deplement) {
        deplementDao.insert(deplement);
    }

    @Override
    public void updateOne(Deplement deplement) {
        deplementDao.updateByPrimaryKeySelective(deplement);
    }

    @Override
    public void deleteOne(String deptid) {
        deplementDao.deleteByPrimaryKey(deptid);
    }

    @Override
    public Integer getCount() {
        return deplementDao.selectCount(new Deplement());
    }
}
