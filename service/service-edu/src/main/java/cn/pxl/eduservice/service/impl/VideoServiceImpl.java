package cn.pxl.eduservice.service.impl;

import cn.pxl.eduservice.entity.Video;
import cn.pxl.eduservice.mapper.VideoMapper;
import cn.pxl.eduservice.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
