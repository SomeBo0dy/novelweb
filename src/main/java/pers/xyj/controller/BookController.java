package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.domain.Book;
import pers.xyj.service.IBookService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(value = "bookControllerApi",tags={"小说操作接口"})
@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;
    @ApiOperation(value="获取所有书本信息")
    @GetMapping
    public Map<String,Object> getAll(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",bookService.list());
        return result;
    }
    @ApiOperation(value="根据id获取书本信息")
    @GetMapping("/{id}")
    public Map<String,Object> getById(@PathVariable Integer id){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",bookService.getById(id));
        return result;
    }
    @ApiOperation(value = "根据提供的id通过书本审核")
    @PostMapping("/pass/{id}")
    public Map<String,Object> PassById(@PathVariable Integer id){
        return bookService.passBook(id);
    }
    //模糊查询
    @ApiOperation(value="根据提供的字符串和当前页数和每页书本数，获得这一页的模糊查询结果")
    @PostMapping("/{currentPage}/{pageSize}")
    public Map<String,Object> search(@PathVariable int currentPage, @PathVariable int pageSize,@RequestParam("bName") String name){
        return bookService.search(currentPage,pageSize,name);
    }
    //种类分页
    @ApiOperation(value="根据提供的种类id和当前页码和每页的书本数获取书本信息")
    @GetMapping("/{type}/{currentPage}/{pageSize}")
    public Map<String,Object> getPageById(@PathVariable int type,@PathVariable int currentPage, @PathVariable int pageSize){
        return bookService.getPageById(type, currentPage, pageSize);
    }
    //分页
    @ApiOperation(value="根据提供页码和每页的书本数获取页面的所有书本信息")
    @GetMapping("/{currentPage}/{pageSize}")
    public Map<String,Object> getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        return bookService.getByPage(currentPage,pageSize);
    }
    @ApiOperation(value="根据提供页码和每页的书本数获取页面的未审核的所有书本信息")
    @GetMapping("/unPassed/{currentPage}/{pageSize}")
    public Map<String,Object> getUPPage(@PathVariable int currentPage, @PathVariable int pageSize){
        return bookService.getUPByPage(currentPage,pageSize);
    }
    @ApiOperation(value="上传书本")
    @PostMapping("upload")
    public Map<String,Object> addBook(@RequestParam("novel") MultipartFile[] files, Book book) throws IOException {
        if (true) throw new IOException();
        return bookService.upload(files,book);
    }
    @ApiOperation(value="根据id删除书本信息")
    @DeleteMapping("/{id}")
    public Map<String,Object> delete(@PathVariable Integer id){
        Map<String,Object> result = new HashMap<>();
        result.put("code",bookService.deleteById(id) ? 200 : 400);
        return result;
    }
    @ApiOperation(value="输出收藏量前10的书")
    @GetMapping("hotList")
    public Map<String,Object> getHotList(){
        return bookService.getHotList();
    }
}
//    @ApiOperation(value="根据提供每页的书本数获取页面个数")
//    @GetMapping("/pages/{pageSize}")
//    public Map<String,Object> getPageNum(@PathVariable int pageSize){
//        Map<String,Object> result = new HashMap<>();
//        int count = bookService.count();
//        result.put("code",200);
//        result.put("message",count % pageSize == 0 ? count/pageSize : count/pageSize + 1);
//        return result;
//    }