package com.zhiran.common.exception;

import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.common.enums.BusinessErrors;

public class BusinessRuntimeException extends  RuntimeException{
    private BusinessErrors businessErrors;

    /**
     * @param businessErrors
     * @return ResponseVo
     */
    public  BusinessRuntimeException(BusinessErrors businessErrors){
        super(businessErrors.getMsg());
        this.businessErrors = businessErrors;
    }

    /**
     * @param businessErrors
     * @param errorMsg 主要是自定义错误信息
     */
    public BusinessRuntimeException (BusinessErrors businessErrors , String errorMsg){
        super(errorMsg);
        this.businessErrors = businessErrors;
    }

    /**
     * @return businessErrors
     */
    public BusinessErrors getBusinessErrors(){
        return this.businessErrors;
    }

}
