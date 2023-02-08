package cn.pxl.eduservice.entity.query;

import lombok.Data;

@Data
public class TeacherQuery extends TimeQuery {
    private String name;
    private int level;
}
