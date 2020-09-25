package com.hong.services.controller;

import com.hong.common.json.JsonResult;
import com.hong.repository.entity.Article;
import com.hong.repository.entity.Category;
import com.hong.repository.entity.Student;
import com.hong.repository.mapper.ArticleMapper;
import com.hong.repository.mapper.CategoryMapper;
import com.hong.repository.mapper.StudentMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 13:21
 * @Version : 1.0
 * Description     :
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/save")
    public String save() {
        Student student = new Student();
        student.setCity("杭州");
        student.setName("码云");
        student.setSchool("杭州师范");
        studentMapper.insert(student);
        return "success";
    }

    @GetMapping("/list")
    @ApiOperation(value="测试获取用户列表")
    public List<Student> list() {
        List<Student> students = studentMapper.selectList(null);
        return students;
    }

    @GetMapping("/findAll")
    public JsonResult findAll() {
        Article article = articleMapper.findById(1L);
        return JsonResult.success(article);
    }

}
