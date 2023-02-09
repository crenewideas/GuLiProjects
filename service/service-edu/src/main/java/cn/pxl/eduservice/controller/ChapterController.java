package cn.pxl.eduservice.controller;


import cn.pxl.eduservice.entity.Chapter;
import cn.pxl.eduservice.entity.Video;
import cn.pxl.eduservice.entity.vo.ChapterVo;
import cn.pxl.eduservice.entity.vo.CoursePublishVo;
import cn.pxl.eduservice.service.ChapterService;
import cn.pxl.eduservice.service.CourseService;
import cn.pxl.eduservice.service.VideoService;
import cn.pxl.result.ResultEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/eduservice/chapter")
@Api(description="课程章节管理")
@CrossOrigin //跨域
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;



    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("/nestedList/{courseId}")
    public ResultEntity<ArrayList<ChapterVo>> nestedList(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){
        ArrayList<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return ResultEntity.success(chapterVoList);
    }

    @ApiOperation(value = "保存章节数据")
    @PostMapping("/save")
    public ResultEntity save(
            @ApiParam(name = "chapter", value = "章节数据", required = true)
            @RequestBody Chapter chapter){
        chapterService.save(chapter);
        return ResultEntity.success();
    }

    @ApiOperation(value = "根据章节id获取章节数据")
    @GetMapping("/getById/{id}")
    public ResultEntity<Chapter> getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable("id") String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return ResultEntity.success(chapter);
    }

    @ApiOperation(value = "根据章节id删除章节数据")
    @DeleteMapping("/removeById/{id}")
    public ResultEntity removeById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable("id") String chapterId){
        chapterService.removeById(chapterId);
        //查询条件：所有章节id为当前章节的小节信息
        QueryWrapper<Video> wrapper = new QueryWrapper<Video>().eq("chapter_id", chapterId);
        //获取所有小节信息的视频id
        List<Video> videos = videoService.list(wrapper);
        //TODO 并且删除云平台的视频
        //同步删除课时内容
        videoService.remove(wrapper);
        return ResultEntity.success();
    }

    @ApiOperation(value = "根据章节id更新章节数据")
    @PutMapping("/updateById/{id}")
    public ResultEntity updateById(
            @ApiParam(name = "chapter", value = "更新章节数据", required = true)
            @RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return ResultEntity.success();
    }

}

