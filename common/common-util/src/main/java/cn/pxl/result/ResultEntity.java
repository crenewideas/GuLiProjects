package cn.pxl.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultEntity <T>{
    @ApiModelProperty(value = "返回消息")
    public String message;
    @ApiModelProperty(value = "返回码")
    public Integer code;
    @ApiModelProperty(value = "是否成功")
    public boolean success;
    @ApiModelProperty(value = "返回token值")
    public String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBY2NvdW50SWQiOiJhZG1pbiIsIm5iZiI6MTYyNDE3NTM4MiwiZXhwIjoxNjI0MTc1NDQyLCJpYXQiOjE2MjQxNzUzODJ9.7p8EHMx1b4-yIMRN7Qxden3nZsDmBvevHEf-3oVhFMg";
    @ApiModelProperty(value = "返回数据")
    public T data;

    public ResultEntity() {
    }

    private ResultEntity(T resultData){
        this(ResultEnum.common_success,resultData);
    }

    private ResultEntity(ResultEnum resultEnum,T resultData){
        this.message = resultEnum.getMessage();
        this.code = resultEnum.getCode();
        this.success = resultEnum.isSuccess();
        this.data = resultData;
    }

    public static <T> ResultEntity<T> success(T resultData){
        return new ResultEntity<>(resultData);
    }

    //分页信息封装。
    public static <T> ResultEntity<PageResultEntity<T>> success(Page<T> pageData){
        PageResultEntity<T> resultEntity = new PageResultEntity<T>(pageData.getCurrent(),pageData.getSize(),pageData.getTotal(),pageData.getPages(),pageData.getRecords());
        return new ResultEntity<>(resultEntity);
    }

    public static <T> ResultEntity<T> success(){
        return new ResultEntity<>((T)"");

    }

    public static <T> ResultEntity<T> success(ResultEnum resultEnum,T resultData){
        return new ResultEntity<>(resultEnum,resultData);
    }

    public static <T> ResultEntity<T> failed(){
        return new ResultEntity<T>(ResultEnum.common_field,(T)"");
    }

    public static <T> ResultEntity<T> failed(String message){
        return new ResultEntity<T>(ResultEnum.common_field,(T)message);
    }

    public static <T> ResultEntity<T> failed(ResultEnum resultEnum,T resultData){
        return new ResultEntity<>(resultEnum,resultData);
    }

}
