package cn.pxl.eduservice.service;

import cn.pxl.eduservice.entity.Course;
import cn.pxl.eduservice.entity.vo.CourseVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-06
 */
public interface CourseService extends IService<Course> {

    /**
     * 保存或者修改课程和课程详情信息
     * @param courseVo
     * @return 新生成或者原有的课程id
     */
    String saveOrUpdateCourseInfo(CourseVo courseVo);

    /**
     * @param id
     * @return 回显课程详情信息
     */
    CourseVo getCourseInfoFormById(String id);
}
