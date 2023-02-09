package cn.pxl.eduservice.controller;

import cn.pxl.eduservice.entity.Course;
import cn.pxl.eduservice.entity.vo.CoursePublishVo;
import cn.pxl.eduservice.entity.vo.CourseQuery;
import cn.pxl.eduservice.entity.vo.CourseVo;
import cn.pxl.eduservice.service.CourseService;
import cn.pxl.result.PageResultEntity;
import cn.pxl.result.ResultEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-06
 */
@Api(description="课程管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增课程或修改课程")
    @PostMapping("/saveCourseInfo")
    public ResultEntity<String> saveCourseInfo(
            @ApiParam(name = "CourseVo", value = "课程基本信息", required = true) @RequestBody CourseVo courseVo){
        String courseId = courseService.saveOrUpdateCourseInfo(courseVo);
        if(!StringUtils.isEmpty(courseId)){
            return ResultEntity.success(courseId);
        } else {
            return ResultEntity.failed();
        }
    }

    @ApiOperation(value = "课程数据信息回显")
    @GetMapping("/getCourseInfoFormById/{id}")
    public ResultEntity<CourseVo> getCourseInfoFormById(
            @ApiParam(name = "id", value = "课程ID", required = true) @PathVariable String id
    ) {
        return ResultEntity.success(courseService.getCourseInfoFormById(id));
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("/queryCourses/{page}/{limit}")
    public ResultEntity<PageResultEntity<Course>> queryCourses(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){
        Page<Course> pageParam = courseService.queryCourses(courseQuery);
        return  ResultEntity.success(pageParam);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("/removeCourseById/{courseId}")
    public ResultEntity removeCourseById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){
        boolean result = courseService.removeCourseById(courseId);
        if(result){
            return ResultEntity.success();
        }else{
            return ResultEntity.failed("删除失败");
        }
    }

}

