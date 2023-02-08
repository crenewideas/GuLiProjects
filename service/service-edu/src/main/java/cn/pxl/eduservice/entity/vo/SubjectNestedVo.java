package cn.pxl.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectNestedVo extends SubjectVo{
    private List<SubjectVo> children = new ArrayList<>();
}