package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.KindDao;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/5/25
 * @time: 下午9:46
 * @desc:
 */
@Service
public class KindService {
    private Logger logger = Logger.getLogger(KindService.class);
    @Autowired
    private KindDao kindDao;

    public List<Kind> getKinds() throws TreeHoleException {
        try {
            return this.kindDao.selectKinds();
        } catch (Exception e) {
            logger.info(e);
            throw new TreeHoleException("查询分类信息错误");
        }
    }

    /**
     * 删除分类信息
     * 实际上是修改状态
     * @param id
     * @throws TreeHoleException
     */
    public void deleteKindById(String id) throws TreeHoleException {
        try {
            this.kindDao.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("删除分类信息错误");
        }
    }
}
