package cn.pxl.eduservice.service.impl;

import cn.pxl.eduservice.entity.Subject;
import cn.pxl.eduservice.entity.excel.ExcelSubjectData;
import cn.pxl.eduservice.entity.excel.listener.SubjectExcelListener;
import cn.pxl.eduservice.entity.vo.SubjectNestedVo;
import cn.pxl.eduservice.entity.vo.SubjectVo;
import cn.pxl.eduservice.mapper.SubjectMapper;
import cn.pxl.eduservice.service.SubjectService;
import cn.pxl.exception.CommonException;
import cn.pxl.result.ResultEntity;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author pxlngu
 * @since 2023-02-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    //添加课程分类
    //poi读取excel内容
    @Override
    public void batchImport(MultipartFile file,SubjectService subjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 这里 需要指定读用哪个 class 去读，然后读取第一个 sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new CommonException(20002,"添加课程分类失败");
        }
    }

    //封装课程展示列表数据。
    @Override
    public ResultEntity<ArrayList<SubjectNestedVo>> nestedList() {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        List<Subject> subjectList = list(queryWrapper);
        //一级分类列表
        ArrayList<Subject> oneSubjects = subjectList.stream().filter(oneSubject -> "0".equals(oneSubject.getParentId())).collect(Collectors.toCollection(ArrayList::new));
        //二级分类列表,
        //key 为 parentId , value 为具体的集合
        Map<String,List<Subject>> twoSubjects = subjectList.stream().filter(oneSubject -> !"0".equals(oneSubject.getParentId())).collect(Collectors.groupingBy(Subject::getParentId));
        //返回的集合对象。
        ArrayList<SubjectNestedVo> subjectNestedVos = new ArrayList<>();
        for (Subject oneSubject : oneSubjects) {
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            subjectNestedVo.setId(oneSubject.getId());
            subjectNestedVo.setTitle(oneSubject.getTitle());
            if(twoSubjects.containsKey(oneSubject.getId())){
                List<SubjectVo> subjectVos = twoSubjects.get(oneSubject.getId()).stream().map(subject -> {
                    SubjectVo subjectVo = new SubjectVo();
                    subjectVo.setId(subject.getId());
                    subjectVo.setTitle(subject.getTitle());
                    return subjectVo;
                }).collect(Collectors.toList());
                subjectNestedVo.setChildren(subjectVos);
            }
            subjectNestedVos.add(subjectNestedVo);
        }
        return ResultEntity.success(subjectNestedVos);
    }
}
