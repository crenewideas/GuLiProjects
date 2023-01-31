package cn.pxl.exception;

import cn.pxl.result.ResultEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer resultCode;
    private String message;

    public CommonException(ResultEnum resultEnum){
        this.setResultCode(Integer.parseInt(resultEnum.getCode()));
        this.setMessage(resultEnum.getMessage());
    }

    @Override
    public String toString() {
        return "CommonException{" +
                "message=" + this.getMessage() +
                ", resultCode=" + resultCode +
                '}';
    }
}
