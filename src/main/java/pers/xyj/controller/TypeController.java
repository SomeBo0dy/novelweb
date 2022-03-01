package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.domain.Type;
import pers.xyj.service.ITypeService;

import java.util.HashMap;
import java.util.Map;
@Api(value = "typeControllerApi",tags={"类型操作接口"})
@RestController
@CrossOrigin
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ITypeService typeService;
    @ApiOperation(value="获得所有的类型")
    @GetMapping
    public Map<String,Object> getAll(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",typeService.list());
        return result;
    }
    @ApiOperation(value="增加新的类型")
    @PostMapping
    public Map<String,Object> save(Type type){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",typeService.save(type));
        return result;
    }
}
//    @ApiOperation(value="根据提供的类型获得符合类型的所有书")
//    @GetMapping("/{id}")
//    public Map<String,Object> getById(@PathVariable Integer id){
//        return typeService.getListById(id);
//    }
//    @ApiOperation(value="根据提供页码和每页的书本数获取所提供类别的所有书本信息")
//    @GetMapping("/{id}/{currentPage}/{pageSize}")
//    public Map<String,Object> getPageById(@PathVariable Integer id,@PathVariable Integer currentPage,@PathVariable Integer pageSize){
//        return typeService.getPageById(id,currentPage,pageSize);
//    }
//    @ApiOperation(value="根据提供类型和每页的书本数，得到该类别的书能分几页")
//    @GetMapping("/{id}/{pageSize}")
//    public Map<String,Object> getTypePageNum(@PathVariable Integer id, @PathVariable Integer pageSize){
//        return typeService.getTypePageNum(id,pageSize);
//    }