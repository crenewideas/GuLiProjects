package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.Teacher;
import cn.pxl.eduservice.entity.query.TeacherQuery;
import cn.pxl.eduservice.service.TeacherService;
import cn.pxl.exception.CommonException;
import cn.pxl.result.PageResultEntity;
import cn.pxl.result.ResultEntity;
import cn.pxl.result.ResultEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author pxlngu
 * @since 2023-01-31
 */
@Api("讲师管理")
@RestController
@CrossOrigin //跨域
@RequestMapping("/eduservice/teacher")
public class TeacherController {
    //
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("/list")
    public ResultEntity<List<Teacher>> list(){
        return ResultEntity.success(teacherService.list(null));
    }

    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("/removeById/{id}")
    public ResultEntity<Boolean> removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        return ResultEntity.success(teacherService.removeById(id));
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("/pageList/{page}/{limit}")
    public ResultEntity<PageResultEntity<Teacher>> pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit

            ,@RequestBody(required = false)  TeacherQuery teacherQuery

    ){
        Page<Teacher> pageParam = new Page<>(page, limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(level != 0) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");
        teacherService.page(pageParam, wrapper);
        return ResultEntity.success(pageParam);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public ResultEntity save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return ResultEntity.success();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getById/{id}")
    public ResultEntity<Teacher> getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        return ResultEntity.success(teacherService.getById(id));
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("/updateById/{id}")
    public ResultEntity updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){
        teacherService.updateById(teacher);
        return ResultEntity.success();
    }

}

