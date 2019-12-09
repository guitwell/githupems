package com.baizhi.service;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.DelCache;
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

    @AddCache("查询一页部门")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Deplement> findByPage(Integer nowpage, Integer pagesize) {
        Deplement deplement = new Deplement();
        Integer thebegin = (nowpage - 1) * pagesize;
        return deplementDao.selectByRowBounds(deplement, new RowBounds(thebegin, pagesize));
    }

    @AddCache("查询一个部门")
    @Override
    public Deplement findOne(String deptid) {
        return deplementDao.selectByPrimaryKey(deptid);
    }

    @DelCache("添加一个部门")
    @Override
    public void addOne(Deplement deplement) {
        deplementDao.insert(deplement);
    }

    @DelCache("更新一个部门")
    @Override
    public void updateOne(Deplement deplement) {
        deplementDao.updateByPrimaryKeySelective(deplement);
    }

    @DelCache("删除一个部门")
    @Override
    public void deleteOne(String deptid) {
        deplementDao.deleteByPrimaryKey(deptid);
    }

    @AddCache("获取部门数量")
    @Override
    public Integer getCount() {
        return deplementDao.selectCount(new Deplement());
    }
}
