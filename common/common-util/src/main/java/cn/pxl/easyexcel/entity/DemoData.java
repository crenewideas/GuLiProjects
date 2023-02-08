package cn.pxl.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

//设置表头和添加的数据字段
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty("学生编号")
    private int sno;

    //设置表头名称
    @ExcelProperty("学生姓名")
    private String sname;

    public int getSno() {
        return sno;
    }
}