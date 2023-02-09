package cn.pxl.eduservice.service.impl;

import cn.pxl.eduservice.entity.Video;
import cn.pxl.eduservice.entity.vo.VideoInfoForm;
import cn.pxl.eduservice.mapper.VideoMapper;
import cn.pxl.eduservice.service.VideoService;
import cn.pxl.exception.CommonException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-08
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.save(video);
        if(!result){
            throw new CommonException(20001, "课时信息保存失败");
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        //从video表中取数据
        Video video = this.getById(id);
        if(video == null){
            throw new CommonException(20001, "数据不存在");
        }

        //创建videoInfoForm对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video, videoInfoForm);

        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        //保存课时基本信息
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.updateById(video);
        if(!result){
            throw new CommonException(20001, "课时信息保存失败");
        }
    }

    @Override
    public boolean removeVideoById(String courseId) {
        //删除视频资源 TODO
        Integer result = baseMapper.deleteById(courseId);
        return null != result && result > 0;
    }
}
