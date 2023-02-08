package cn.pxl.ossservice.beans;

import cn.pxl.exception.CommonException;
import cn.pxl.exception.ExceptionUtil;
import cn.pxl.result.ResultEntity;
import cn.pxl.result.ResultEnum;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class OssExceptionHandler {

    @ExceptionHandler(OSSException.class)
    @ResponseBody
    public ResultEntity<String> ossException(OSSException e){
        e.printStackTrace();
        String message = "上传文件异常，异常原因：" + e.getMessage();
        log.error(message);
        log.error(ExceptionUtil.getMessage(e));
        return ResultEntity.failed(ResultEnum.upload_field,message);
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public ResultEntity<String> ossException(ClientException e){
        e.printStackTrace();
        String message = "上传文件客户端异常，异常原因：" + e.getMessage();
        log.error(message);
        log.error(ExceptionUtil.getMessage(e));
        return ResultEntity.failed(ResultEnum.upload_client_field, message);
    }

}
