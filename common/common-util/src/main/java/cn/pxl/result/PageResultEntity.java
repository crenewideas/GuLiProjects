package cn.pxl.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public  class PageResultEntity <T>{
    private long pageNbr;
    private long pageSize;
    private long totalNbr;
    private long totalSize;
    private List<T> data;
}