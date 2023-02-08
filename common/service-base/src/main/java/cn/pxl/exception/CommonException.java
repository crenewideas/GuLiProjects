package cn.pxl.exception;

import cn.pxl.result.ResultEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CommonException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer resultCode;
    private String message;

    public CommonException(ResultEnum resultEnum){
        this.setResultCode(resultEnum.getCode());
        this.setMessage(resultEnum.getMessage());
    }

    public CommonException(Integer resultCode,String message){
        this.setResultCode(resultCode);
        this.setMessage(message);
    }

    @Override
    public String toString() {
        return "CommonException{" +
                "message=" + this.getMessage() +
                ", resultCode=" + resultCode +
                '}';
    }
}
