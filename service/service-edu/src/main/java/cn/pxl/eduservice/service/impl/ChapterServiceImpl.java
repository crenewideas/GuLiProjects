package cn.pxl.eduservice.service.impl;

import cn.pxl.eduservice.entity.Chapter;
import cn.pxl.eduservice.entity.Video;
import cn.pxl.eduservice.entity.vo.ChapterVo;
import cn.pxl.eduservice.entity.vo.VideoVo;
import cn.pxl.eduservice.mapper.ChapterMapper;
import cn.pxl.eduservice.service.ChapterService;
import cn.pxl.eduservice.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-08
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private VideoService videoService;

    @Override
    public ArrayList<ChapterVo> nestedList(String courseId) {

        //获取章节信息
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.orderByAsc("sort", "id");

        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVos = baseMapper.selectList(wrapper).stream().map(oneChapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(oneChapter, chapterVo);
            return chapterVo;
        }).collect(Collectors.toCollection(ArrayList::new));

        //获取课时信息
        QueryWrapper<Video> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        Map<String, ArrayList<VideoVo>> videoMap = videoService.list(queryWrapper2).stream().map(video->{
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(video,videoVo);
            return videoVo;
        }).collect(Collectors.groupingBy(VideoVo::getChapterId,Collectors.toCollection(ArrayList::new)));

        //封装课时信息到章节信息中。
        for (ChapterVo chapterVo : chapterVos) {
            String id = chapterVo.getId();
            ArrayList<VideoVo> children = chapterVo.getChildren() == null ? new ArrayList<>() : chapterVo.getChildren();
            if(videoMap.containsKey(id)){
                children.addAll(videoMap.get(id));
            }
            chapterVo.setChildren(children);
        }
        return chapterVos;
    }

    @Override
    public void removeChapterById(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        remove(wrapper);
    }
}
