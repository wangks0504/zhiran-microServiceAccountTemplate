package com.zhiran.configuration;

import com.netflix.client.ClientException;
import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.common.exception.BusinessRuntimeException;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobleExceptionHander {
    /**
     * 获取运行时的错误
     * 错误谁都会犯重要的是怎么解决错误并且减少错误对你造成的影响 POWER BY wangks0504
     */
    @ExceptionHandler(value = BusinessRuntimeException.class)
    @ResponseBody
    public ResponseVO getBusinessRunTimeException(HttpServletRequest hsr,BusinessRuntimeException e){
        return ResponseVO.error(e.getBusinessErrors());
    }
    /**
     * 获取客户端的错误
     */
    @ExceptionHandler(value = ClientException.class)
    @ResponseBody
    public ResponseVO getClientException(HttpServletRequest hsr , ClientException e){
        return ResponseVO.error(e.getErrorMessage());
    }

    /**
     * 获取方法错误
     */
    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVO getMethodArgumentNotValidException(HttpServletRequest hsr ,MethodArgumentNotValidException e){
        List<String> error  = e.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
       return ResponseVO.error(String.join(","+error));
    }






}
