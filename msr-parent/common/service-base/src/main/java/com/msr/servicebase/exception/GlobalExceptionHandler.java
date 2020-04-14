package com.msr.servicebase.exception;

import com.msr.commonutils.ExceptionUtil;
import com.msr.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j//添加@Slf4j后可以使用日志对象，把日志输出到文件
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return  R.error().message("全局异常！！！");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了自定义异常");
    }

   //自定义异常
    @ExceptionHandler(MSRException.class)
    @ResponseBody
    public R error(MSRException e){
        log.error(e.getMessage());//将错误日志输出到文件
        log.error(ExceptionUtil.getMessage(e));//将日志堆栈信息输出到文件
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
