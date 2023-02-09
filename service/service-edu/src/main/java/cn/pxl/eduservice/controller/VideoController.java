package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.vo.VideoInfoForm;
import cn.pxl.eduservice.service.VideoService;
import cn.pxl.result.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-08
 */
@Api(description="课时管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("/saveVideoInfo")
    public ResultEntity save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm){
        videoService.saveVideoInfo(videoInfoForm);
        return ResultEntity.success();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("/getVideoInfoById/{id}")
    public ResultEntity<VideoInfoForm> getVideoInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){
        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return ResultEntity.success(videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("/updateVideoInfoById/{id}")
    public ResultEntity updateVideoInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm,

            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        videoService.updateVideoInfoById(videoInfoForm);
        return ResultEntity.success();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("/removeById/{id}")
    public ResultEntity removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){
        boolean result = videoService.removeVideoById(id);
        if(result){
            return ResultEntity.success();
        }else{
            return ResultEntity.failed("删除失败");
        }
    }

}

