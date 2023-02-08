package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.Course;
import cn.pxl.eduservice.entity.CourseDescription;
import cn.pxl.eduservice.entity.vo.CourseVo;
import cn.pxl.eduservice.service.CourseService;
import cn.pxl.exception.CommonException;
import cn.pxl.result.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

