package cn.pxl.easyexcel;

import cn.pxl.easyexcel.entity.DemoData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

public class MyDemo {

    public static void main(String[] args) {
        // 写法1
        String fileName = "/Users/pengxiaoliang/Downloads/easyExcel.xlsx";
        String fileName2 = "/Users/pengxiaoliang/Downloads/easyExcel2.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用 03 则 传入 excelType 参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("写入方法一").doWrite(data());

        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName2, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
        excelWriter.write(data(), writeSheet);
        /// 千万别忘记 finish 会帮忙关闭流
        excelWriter.finish();

    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
