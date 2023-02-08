package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.Teacher;
import cn.pxl.eduservice.entity.User;
import cn.pxl.eduservice.service.TeacherService;
import cn.pxl.exception.CommonException;
import cn.pxl.result.PageResultEntity;
import cn.pxl.result.ResultEntity;
import cn.pxl.result.ResultEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api("用户管理")
@RestController
@CrossOrigin //跨域
@RequestMapping("/eduservice/user")
public class UserController {
    //
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResultEntity login(@RequestBody User user){
        ResultEntity<Object> success = ResultEntity.success();
        return success;
    }

    @ApiOperation(value = "获取token")
    @GetMapping("/info")
    public ResultEntity<String> info(String token){
        return ResultEntity.success();
    }


}

