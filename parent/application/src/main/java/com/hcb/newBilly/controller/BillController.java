package com.hcb.newBilly.controller;

import com.github.pagehelper.Page;
import com.hcb.newBilly.common.Constants;
import com.hcb.newBilly.common.JwtUtils;
import com.hcb.newBilly.common.Result;
import com.hcb.newBilly.dto.*;
import com.hcb.newBilly.po.BillPO;
import com.hcb.newBilly.service.BillService;
import com.hcb.newBilly.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "账单管理")
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @ApiOperation("查询账单")
    @ResponseBody
    @RequestMapping(value = "/findBill", method = RequestMethod.POST)
    public ResultVO findUserByUserName(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody BillFindDTO billFindDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            billFindDTO.setOperator(userId);
            Page<BillPO> billPOs = billService.findBillPO(billFindDTO);
            PageVO<BillPO> billPOPageVO = new PageVO<>(billPOs);
            return Result.ok(billPOPageVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("添加账单")
    @ResponseBody
    @RequestMapping(value = "/saveBill", method = RequestMethod.POST)
    public ResultVO saveBill(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody BillDTO billDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            billService.saveBill(billDTO, userId);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("更新账单")
    @ResponseBody
    @RequestMapping(value = "/updateBill", method = RequestMethod.POST)
    public ResultVO updateBill(@RequestBody BillDTO billDTO) {
        try {
            billService.updateBill(billDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("删除账单")
    @ResponseBody
    @RequestMapping(value = "/deleteBill", method = RequestMethod.POST)
    public ResultVO deleteBill(@RequestBody IdDTO idDTO) {
        try {
            billService.deleteBill(idDTO.getId());
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    /****************************************统计*********************************************************/
    @ApiOperation("按年月日统计消费情况")
    @ResponseBody
    @RequestMapping(value = "/findByYMD", method = RequestMethod.POST)
    public ResultVO findByYMD(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody StatYmdDTO statYmdDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            statYmdDTO.setOperator(userId);
            YMDStaticsVO ymdStatics = billService.getYMDStatics(statYmdDTO);
            return Result.ok(ymdStatics);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }
    @ApiOperation("按时间查询消费情况")
    @ResponseBody
    @RequestMapping(value = "/findByTime", method = RequestMethod.POST)
    public ResultVO findByTime(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody StatYmdDTO statYmdDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            statYmdDTO.setOperator(userId);
            List<BillPO> billPOList = billService.findByTime(statYmdDTO);
            return Result.ok(billPOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("按年字典统计消费情况")
    @ResponseBody
    @RequestMapping(value = "/findBySelect", method = RequestMethod.POST)
    public ResultVO findBySelect(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody StatDictionaryDTO statisticsBillDicDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            statisticsBillDicDTO.setOperator(userId);
            StatTypeVO statType = billService.getStatType(statisticsBillDicDTO);
            return Result.ok(statType);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("按年字典统计消费情况")
    @ResponseBody
    @RequestMapping(value = "/findTypeList", method = RequestMethod.POST)
    public ResultVO findTypeList(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody StatTypeDTO statisticsTypeBillDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            statisticsTypeBillDTO.setOperator(userId);
            List<BillPO> billPOS = billService.findTypeBill(statisticsTypeBillDTO);
            return Result.ok(billPOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }
}
