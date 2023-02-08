package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.Subject;
import cn.pxl.eduservice.entity.vo.SubjectNestedVo;
import cn.pxl.eduservice.service.SubjectService;
import cn.pxl.result.ResultEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}

