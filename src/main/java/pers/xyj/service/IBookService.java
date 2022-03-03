package pers.xyj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.domain.Book;

import java.util.Map;

public interface IBookService extends IService<Book> {

    Map<String, Object> upload(MultipartFile[] files, Book book);

    Map<String, Object> getByPage(int currentPage, int pageSize);

    Map<String, Object> search(int currentPage, int pageSize, String name);

    boolean deleteById(Integer id);

    Map<String, Object> getHotList();

    Map<String, Object> getPageById(int type, int currentPage, int pageSize);

    Map<String, Object> passBook(Integer id);

    Map<String, Object> getUPByPage(int currentPage, int pageSize);
}
/*
public interface BookService {
    Boolean save(Book book);
    Boolean update(Book book);
    Boolean delete(Integer id);
    Book getById(Integer id);
    List<Book> getAll();
    IPage<Book> getPage(Integer currentPage,Integer pageSize);
}
 */