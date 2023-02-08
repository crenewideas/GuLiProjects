package cn.pxl.eduservice.entity.query;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TimeQuery {
    private String begin;
    private String end;
}
