package pers.xyj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.domain.Type;

import java.util.Map;

public interface ITypeService extends IService<Type> {
    Map<String,Object> getListById(Integer id);

    Map<String, Object> getTypePageNum(Integer id, Integer pageSize);

    Map<String, Object> getPageById(Integer id, Integer currentPage, Integer pageSize);
}
