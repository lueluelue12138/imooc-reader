package com.imooc.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import com.imooc.reader.utils.ResponseUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/management/book")
public class MBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtils list(Integer page, Integer rows) {
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        ResponseUtils resp = null;
        try {
            IPage p = bookService.selectBookMap(page, rows);
            resp = new ResponseUtils().put("list", p.getRecords()).put("count", p.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/upload")
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        //得到文件上传目录
        String uploadPath = request.getServletContext().getResource("/").getPath() + "/upload/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uploadPath + fileName + suffix));
        Map result = new LinkedHashMap();
        result.put("errno", 0);
        result.put("data", new String[]{"/upload/" + fileName + suffix});
        return result;
    }

    @PostMapping("/create")
    public ResponseUtils createBook(Book book) {
        ResponseUtils resp = null;
        try {
            System.out.println(book.getDescription());
            Document doc = Jsoup.parse(book.getDescription());
            Elements elements = doc.select("img");
            if (elements.size() == 0) {
                resp = new ResponseUtils("ImageNotFoundError", "图片描述未包含封面图片");
                return resp;
            }
            String cover = elements.first().attr("src");
            book.setCover(cover);
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            Book b = bookService.createBook(book);
            resp=new ResponseUtils().put("book",b);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/update")
    public ResponseUtils updateBook(Book book){
        ResponseUtils resp= null;
        try {
            Document doc = Jsoup.parse(book.getDescription());
            Elements elements = doc.select("img");
            if (elements.size() == 0) {
                resp = new ResponseUtils("ImageNotFoundError", "图片描述未包含封面图片");
                return resp;
            }
            String cover = elements.first().attr("src");
            Book b = bookService.selectById(book.getBookId());
            b.setBookName(book.getBookName());
            b.setSubTitle(book.getSubTitle());
            b.setAuthor(book.getAuthor());
            b.setCategoryId(book.getCategoryId());
            b.setDescription(book.getDescription());
            b.setCover(cover);
            bookService.updateBook(b);
            resp=new ResponseUtils().put("book",b);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/delete")
    public ResponseUtils deleteBook(Long bookId){
        ResponseUtils resp =null;
        try{
            bookService.deleteBook(bookId);
            resp=new ResponseUtils();
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
}