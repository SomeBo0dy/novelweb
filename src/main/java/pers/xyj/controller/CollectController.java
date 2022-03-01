package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.domain.Collect;
import pers.xyj.service.ICollectService;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@Api(value = "collectControllerApi",tags={"收藏夹操作接口"})
@RestController
@RequestMapping("/collects")
public class CollectController {
    @Autowired
    private ICollectService collectService;
    @ApiOperation(value="根据收藏类上传收藏夹信息")
    @PostMapping
    public Map<String,Object> addCollection(Collect collect){
        return collectService.addCollection(collect);
    }
    @ApiOperation(value="根据收藏类删除收藏夹信息")
    @DeleteMapping
    public Map<String,Object> deleteCol(Collect collect){
        return collectService.deleteCol(collect);
    }
    @ApiOperation(value="根据提供的用户id和当前页码和每页的书本数获取书本信息")
    @GetMapping("/{uId}/{currentPage}/{pageSize}")
    public Map<String,Object> getColById(@PathVariable int uId,@PathVariable int currentPage, @PathVariable int pageSize){
        return collectService.getColById(uId, currentPage, pageSize);
    }

}
