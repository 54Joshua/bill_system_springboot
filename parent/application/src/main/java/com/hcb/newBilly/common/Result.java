package com.hcb.newBilly.common;

import com.hcb.newBilly.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;

public class Result {

    public static ResultVO ok(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setIsSuccess(true);
        resultVO.setMsg("操作成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO ok() {
        ResultVO resultVO = new ResultVO();
        resultVO.setIsSuccess(true);
        resultVO.setMsg("操作成功");
        resultVO.setData(null);
        return resultVO;
    }

    public static ResultVO err(String errMsg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setIsSuccess(false);
        resultVO.setData(null);
        if (StringUtils.isBlank(errMsg)) {
            errMsg = "操作失败";
        }
        resultVO.setMsg(errMsg);
        return resultVO;
    }
}
