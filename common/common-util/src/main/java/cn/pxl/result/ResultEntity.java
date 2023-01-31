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
    public String resultCode;
    @ApiModelProperty(value = "是否成功")
    public boolean isSuccess;
    @ApiModelProperty(value = "返回数据")
    public T resultData;

    public ResultEntity() {
    }

    private ResultEntity(T resultData){
        this(ResultEnum.common_success,resultData);
    }

    private ResultEntity(ResultEnum resultEnum,T resultData){
        this.message = resultEnum.getMessage();
        this.resultCode = resultEnum.getCode();
        this.isSuccess = resultEnum.isSuccess();
        this.resultData = resultData;
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

    public static <T> ResultEntity<T> failed(Integer resultCode,String message){
        ResultEntity<T> result = new ResultEntity<>(ResultEnum.common_field, (T) message);
        result.setResultCode(resultCode.toString());
        return result;
    }

}
