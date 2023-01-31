package cn.pxl.component;

import cn.pxl.exception.CommonException;
import cn.pxl.exception.ExceptionUtil;
import cn.pxl.result.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)

	@ResponseBody
	public ResultEntity<String> error(Exception e){
		e.printStackTrace();
		log.error(e.getMessage());
		return ResultEntity.failed(e.getMessage());
	}

	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public ResultEntity<String> error(ArithmeticException e){
		e.printStackTrace();
		String message = "执行了自定义异常:算数运算异常！异常原因：" + e.getMessage();
		log.error(message);
		return ResultEntity.failed(message);
	}

	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public ResultEntity<String> error(CommonException e){
		e.printStackTrace();
		String message = "业务异常，异常原因：" + e.getMessage();
		log.error(message);//业务异常，异常原因：业务处理异常！
		log.error(ExceptionUtil.getMessage(e));//CommonException(resultCode=111112, message=业务处理异常！)
		return ResultEntity.failed(message);
	}

}