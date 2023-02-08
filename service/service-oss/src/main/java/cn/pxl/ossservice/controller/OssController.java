package cn.pxl.ossservice.controller;

import cn.pxl.ossservice.service.intf.FileUploadService;
import cn.pxl.result.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/ossservice/file")
public class OssController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 头像图片文件上传
     *
     * @param file
     */
    @ApiOperation(value = "头像图片文件上传")
    @PostMapping("/uploadImage")
    public ResultEntity<String> uploadImage(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        //返回r对象
        String uploadUrl = fileUploadService.uploadImage(file);
        return ResultEntity.success(uploadUrl);
    }

    /**
     * excel 模版文件上传
     * @param file
     */
    @ApiOperation(value = "Excel文件上传")
    @PostMapping("/uploadExcel")
    public ResultEntity<String> uploadExcel(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        //返回r对象
        String uploadUrl = fileUploadService.uploadExcel(file);
        return ResultEntity.success(uploadUrl);
    }

    /**
     * 课程封面图片文件上传
     * @param file
     */
    @ApiOperation(value = "课程封面图片文件上传")
    @PostMapping("/uploadCourseCover")
    public ResultEntity<String> uploadCourseCover(
            @ApiParam(name = "file", value = "文件", required = true) @RequestParam("file") MultipartFile file
    ) {
        //返回 路径 对象
        String uploadUrl = fileUploadService.uploadCourseCover(file);
        return ResultEntity.success(uploadUrl);
    }

}
