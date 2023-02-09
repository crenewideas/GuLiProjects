package cn.pxl.eduservice.service.impl;

import cn.pxl.eduservice.entity.Course;
import cn.pxl.eduservice.entity.CourseDescription;
import cn.pxl.eduservice.entity.Teacher;
import cn.pxl.eduservice.entity.vo.CoursePublishVo;
import cn.pxl.eduservice.entity.vo.CourseQuery;
import cn.pxl.eduservice.entity.vo.CourseVo;
import cn.pxl.eduservice.mapper.CourseMapper;
import cn.pxl.eduservice.service.ChapterService;
import cn.pxl.eduservice.service.CourseDescriptionService;
import cn.pxl.eduservice.service.CourseService;
import cn.pxl.eduservice.service.VideoService;
import cn.pxl.exception.CommonException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-06
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

    @Override
    public String saveOrUpdateCourseInfo(CourseVo courseVo) {
        //如果 课程id 为空，那么是新增课程
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseVo,course);
        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseVo.getDescription());
        if(StringUtils.isEmpty(course.getId())){
            course.setStatus(Course.COURSE_DRAFT);
            boolean resultCourseInfo = this.save(course);
            courseDescription.setId(course.getId());
            if(!resultCourseInfo){
                throw new CommonException(20001, "课程信息保存失败");
            }
            boolean resultDescription = courseDescriptionService.save(courseDescription);
            if(!resultDescription){
                throw new CommonException(20001, "课程详情信息保存失败");
            }
            //如果 课程id不为空，那么执行更新方法
        }else {
            courseDescription.setId(course.getId());
            boolean resultCourseInfo = this.updateById(course);
            if(!resultCourseInfo){
                throw new CommonException(20001, "课程信息更新失败");
            }
            boolean resultDescription = courseDescriptionService.updateById(courseDescription);
            if(!resultDescription){
                throw new CommonException(20001, "课程详情信息更新失败");
            }
        }
        return course.getId();
    }

    @Override
    public CourseVo getCourseInfoFormById(String id) {
        Course course = this.getById(id);
        if(course == null){
            throw new CommonException(20001, "数据不存在");
        }
        CourseVo courseInfoForm = new CourseVo();
        BeanUtils.copyProperties(course, courseInfoForm);
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        if(courseDescription != null){
            courseInfoForm.setDescription(courseDescription.getDescription());
        }
        return courseInfoForm;
    }

    @Override
    public CoursePublishVo getCoursePublishInfoById(String id) {
        return baseMapper.getCoursePublishInfoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        Integer count = baseMapper.updateById(course);
        return null != count && count > 0;
    }

    @Override
    public Page<Course> queryCourses(CourseQuery courseQuery) {
        Page<Course> coursePage = new Page<>();
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        wrapper.orderByDesc("gmt_create");
        page(coursePage,wrapper);
        return coursePage;
    }

    @Override
    public boolean removeCourseById(String courseId) {
        removeById(courseId);
        courseDescriptionService.removeById(courseId);
        chapterService.removeChapterById(courseId);
        videoService.removeVideoById(courseId);
        return true;
    }
}
