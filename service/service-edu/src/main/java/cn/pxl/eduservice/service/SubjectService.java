package cn.pxl.eduservice.service;

import cn.pxl.eduservice.entity.Subject;
import cn.pxl.eduservice.entity.vo.SubjectNestedVo;
import cn.pxl.result.ResultEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-05
 */
public interface SubjectService extends IService<Subject> {
    void batchImport(MultipartFile file,SubjectService subjectService);
    ResultEntity<ArrayList<SubjectNestedVo>> nestedList();
}
