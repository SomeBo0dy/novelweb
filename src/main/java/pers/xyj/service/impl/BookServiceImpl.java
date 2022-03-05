package pers.xyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.dao.BookDao;
import pers.xyj.domain.Book;
import pers.xyj.service.IBookService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public Map<String, Object> upload(MultipartFile[] files, Book book) {
        Map<String,Object> resultMap = new HashMap<>();
        if (files.length != 2 || files[0] == null || files[1] == null){
            resultMap.put("code",400);
            resultMap.put("message","上传失败");
            return resultMap;
        }
        String fileName;
        String suffixName;
        String filePath;
        for (int i = 0; i < files.length; i++) {
            fileName = files[i].getOriginalFilename();
            suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println(suffixName);
            Date cur = new Date();
            if (suffixName.equals(".txt")) {
                filePath = "/www/wwwroot/text/"+ cur.getTime();
                //filePath = "C:\\Users\\97908\\Desktop\\txt\\"+ cur.getTime();
                book.setTextPath(filePath +fileName);
            }else{
                filePath = "/www/wwwroot/img/"+ cur.getTime();
                //filePath = "C:\\Users\\97908\\Desktop\\img\\"+ cur.getTime();
                book.setImgPath(filePath + fileName);
            }
            File dest = new File(filePath + fileName);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            try {
                files[i].transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int result = bookDao.insert(book);
        if (result > 0){
            resultMap.put("code",200);
            resultMap.put("message","上传成功");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","上传失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getByPage(int currentPage, int pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.eq("pass",1);
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,qw);
        resultMap.put("message",page);
        return resultMap;
    }

    @Override
    public Map<String, Object> search(int currentPage, int pageSize, String name) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.like("b_name",name);
        qw.eq("pass",1);
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,qw);
        resultMap.put("message",page);
        return resultMap;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean flag;
        Book book = bookDao.selectById(id);
        File file1 = new File(book.getImgPath());
        File file2 = new File(book.getTextPath());
        if (bookDao.deleteById(id) > 0){
            file1.delete();
            file2.delete();
            bookDao.deleteCol(id);
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getHotList() {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        resultMap.put("message",bookDao.getHotList());
        return resultMap;
    }

    @Override
    public Map<String, Object> getPageById(int type, int currentPage, int pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.eq("t_id",type);
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,qw);
        resultMap.put("message",page);
        return resultMap;
    }

    @Override
    public Map<String, Object> passBook(Integer id) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        int count = bookDao.passBook(id);
        String mes;
        if (count > 0)
            mes = "审核通过";
        else
            mes = "审核失败";
        resultMap.put("message",mes);
        return resultMap;
    }

    @Override
    public Map<String, Object> getUPByPage(int currentPage, int pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",200);
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.eq("pass",0);
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,qw);
        resultMap.put("message",page);
        return resultMap;
    }

}
/*
@Service
public class BookServiceImpl2 implements BookService {

    @Autowired
    private BookDao bookDao;


    @Override
    public Boolean save(Book book) {
        return bookDao.insert(book) > 0;
    }

    @Override
    public Boolean update(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.selectList(null);
    }

    @Override
    public IPage<Book> getPage(Integer currentPage, Integer pageSize) {
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,null);
        return page;
    }
}
 */