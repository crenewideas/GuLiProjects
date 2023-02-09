package cn.pxl.eduservice.service;

import cn.pxl.eduservice.entity.Chapter;
import cn.pxl.eduservice.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-08
 */
public interface ChapterService extends IService<Chapter> {
    ArrayList<ChapterVo> nestedList(String courseId);

    void removeChapterById(String courseId);
}
