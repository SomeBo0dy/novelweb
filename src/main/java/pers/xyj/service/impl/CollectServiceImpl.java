package pers.xyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.dao.CollectDao;
import pers.xyj.domain.Book;
import pers.xyj.domain.Collect;
import pers.xyj.domain.User;
import pers.xyj.service.ICollectService;

import java.util.HashMap;
import java.util.Map;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectDao, Collect> implements ICollectService {
    @Autowired
    private CollectDao collectDao;
    @Override
    public Map<String,Object> deleteCol(Collect collect) {
        Map<String,Object> result = new HashMap<>();
        int res = collectDao.deleteCol(collect.getBId(),collect.getUId());
        if (res > 0) {
            result.put("code", 200);
            result.put("message", "取消收藏成功");
            collectDao.descCount(collect.getBId());
        }else{
            result.put("code", 400);
            result.put("message", "操作失败");
        }
        return result;
    }

    @Override
    public void addCount(Collect collect) {
        collectDao.addCount(collect.getBId());
    }

    @Override
    public Map<String, Object> getColById(int uId, int currentPage, int pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        IPage<Book> page = new Page(currentPage,pageSize);
        collectDao.querByPage(page,uId);
        resultMap.put("message",page);
        return resultMap;
    }

    @Override
    public Map<String, Object> addCollection(Collect collect) {
        Map<String, Object> resultMap = new HashMap<>();
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("b_id",collect.getBId());
        wrapper.eq("u_id",collect.getUId());
        Integer count = collectDao.selectCount(wrapper);
        if (count > 0){
            resultMap.put("code",400);
            resultMap.put("message","已在收藏夹中");
            return resultMap;
        }
        int result = collectDao.insert(collect);
        if (result > 0){
            resultMap.put("code",200);
            resultMap.put("message","收藏成功");
            collectDao.addCount(collect.getBId());
        }else{
            resultMap.put("code",400);
            resultMap.put("message","收藏失败");
        }
        return resultMap;
    }
}
