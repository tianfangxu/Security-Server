package com.mot.model;

import com.mot.common.constant.CommonConstant;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultBaseModel {

    private int state;
    private String message;
    private Object result;

    public static ResultBaseModel getSuccess(){
        ResultBaseModel model = new ResultBaseModel();
        model.setState(CommonConstant.RESULT_SUCCESS_STATE);
        return model;
    }

    public static ResultBaseModel getFailure(){
        ResultBaseModel model = new ResultBaseModel();
        model.setState(CommonConstant.RESULT_FAILURE_STATE);
        return model;
    }

    public static ResultBaseModel getError(){
        ResultBaseModel model = new ResultBaseModel();
        model.setState(CommonConstant.RESULT_ERROR_STATE);
        return model;
    }
}
