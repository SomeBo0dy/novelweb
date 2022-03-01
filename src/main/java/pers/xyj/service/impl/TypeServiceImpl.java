package pers.xyj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.dao.TypeDao;
import pers.xyj.domain.Type;
import pers.xyj.service.ITypeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao,Type> implements ITypeService {
    @Autowired
    private TypeDao typeDao;
    @Override
    public Map<String,Object> getListById(Integer id) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        resultMap.put("message",typeDao.getListById(id));
        return resultMap;
    }

    @Override
    public Map<String, Object> getTypePageNum(Integer id, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        int count = typeDao.getTypePageNum(id);
        resultMap.put("message",count % pageSize == 0 ? count/pageSize : count/pageSize + 1 );
        return resultMap;
    }

    @Override
    public Map<String, Object> getPageById(Integer id, Integer currentPage, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        resultMap.put("message",typeDao.getPageById(id,(currentPage - 1) * pageSize,pageSize));
        return resultMap;
    }
}
