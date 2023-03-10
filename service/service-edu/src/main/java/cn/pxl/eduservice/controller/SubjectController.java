package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.Subject;
import cn.pxl.eduservice.entity.vo.CoursePublishVo;
import cn.pxl.eduservice.entity.vo.SubjectNestedVo;
import cn.pxl.eduservice.service.CourseService;
import cn.pxl.eduservice.service.SubjectService;
import cn.pxl.result.ResultEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-05
 */
@Api(description="课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    //添加课程分类
    @ApiOperation(value = "Excel批量导入数据")
    @PostMapping("/addSubject")
    public ResultEntity addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        subjectService.batchImport(file,subjectService);
        //判断返回集合是否为空
        return ResultEntity.success();
    }

    //查询课程分类
    @ApiOperation(value = "Excel批量导入数据")
    @GetMapping("/getNestedTreeList")
    public ResultEntity<ArrayList<SubjectNestedVo>> getNestedTreeList() {
        //1 获取全部课程信息
        return subjectService.nestedList();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("/getCoursePublishInfoById/{id}")
    public ResultEntity<CoursePublishVo> getCoursePublishInfoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo courseInfoForm = courseService.getCoursePublishInfoById(id);
        return ResultEntity.success(courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("/publishCourseById/{id}")
    public ResultEntity publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        courseService.publishCourseById(id);
        return ResultEntity.success();
    }
}

