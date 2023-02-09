package cn.pxl.eduservice.service;

import cn.pxl.eduservice.entity.Course;
import cn.pxl.eduservice.entity.vo.CoursePublishVo;
import cn.pxl.eduservice.entity.vo.CourseQuery;
import cn.pxl.eduservice.entity.vo.CourseVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    //查询发布课程信息
    CoursePublishVo getCoursePublishInfoById(String id);

    //根据id发布课程
    boolean publishCourseById(String id);

    //前端页面添加课程之后，或者点击课程论列表之后的课程列表显示
    Page<Course> queryCourses(CourseQuery courseQuery);

    //根据id删除课程信息、课程描述信息、章节信息、远程视频信息等。
    boolean removeCourseById(String id);
}
