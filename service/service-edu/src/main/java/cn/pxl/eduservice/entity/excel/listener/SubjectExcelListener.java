package cn.pxl.eduservice.entity.excel.listener;

import cn.pxl.eduservice.entity.Subject;
import cn.pxl.eduservice.entity.excel.ExcelSubjectData;
import cn.pxl.eduservice.service.SubjectService;
import cn.pxl.exception.CommonException;
import cn.pxl.result.ResultEnum;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public SubjectService subjectService;

    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取 excel 内容
    @Override
    public void invoke(ExcelSubjectData oneSubjectNames, AnalysisContext analysisContext) {
        if(oneSubjectNames == null) {
            throw new CommonException(ResultEnum.excel_data_read_field);
        }
        //添加一级分类
        Subject existOneSubject = this.existOneSubject(subjectService,oneSubjectNames.getOneSubjectName());
        if(existOneSubject == null) {//没有相同的
            existOneSubject = new Subject();
            existOneSubject.setTitle(oneSubjectNames.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        Subject existTwoSubject = this.existTwoSubject(subjectService,oneSubjectNames.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new Subject();
            existTwoSubject.setTitle(oneSubjectNames.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

    //判断一级分类是否重复
    private Subject existTwoSubject(SubjectService subjectService,String name,String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }

    //判断一级分类是否重复
    private Subject existOneSubject(SubjectService subjectService,String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        //查询条件一：
        wrapper.eq("title",name);
        //查询条件二：一级分类（一级分类的 parent_id 为 0。）
        wrapper.eq("parent_id","0");
        return subjectService.getOne(wrapper);
    }
}