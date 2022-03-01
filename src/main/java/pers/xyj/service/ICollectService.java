package pers.xyj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.domain.Collect;

import java.util.Map;

public interface ICollectService extends IService<Collect> {
    Map<String,Object> deleteCol(Collect collect);

    void addCount(Collect collect);

    Map<String, Object> getColById(int uId, int currentPage, int pageSize);

    Map<String, Object> addCollection(Collect collect);
}
