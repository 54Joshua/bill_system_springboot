package com.hcb.newBilly.controller;

import com.hcb.newBilly.common.Constants;
import com.hcb.newBilly.common.JwtUtils;
import com.hcb.newBilly.common.Result;
import com.hcb.newBilly.dto.EnNameDTO;
import com.hcb.newBilly.dto.IdDTO;
import com.hcb.newBilly.po.DictionaryPO;
import com.hcb.newBilly.service.DictionaryService;
import com.hcb.newBilly.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api("字典管理")
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    @ApiOperation("查询字典列表")
    @ResponseBody
    @RequestMapping(value = "/findDictionary", method = RequestMethod.POST)
    public ResultVO findDictionary(@RequestHeader(Constants.AUTHORIZATION) String token, @RequestBody EnNameDTO enNameDTO) {
        try {
            String userId = JwtUtils.getUserId(token);
            List<DictionaryPO> dictionaryPOS = dictionaryService.findDictionaryByEnName(enNameDTO.getEnName(),userId);
            return Result.ok(dictionaryPOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("保存字典")
    @ResponseBody
    @RequestMapping(value = "/saveDictionary", method = RequestMethod.POST)
    public ResultVO saveDictionary(@RequestHeader(Constants.AUTHORIZATION) String token,@RequestBody DictionaryPO dictionaryPO) {
        try {
            String userId = JwtUtils.getUserId(token);
            dictionaryPO.setOwner(userId);
            dictionaryService.saveDictionary(dictionaryPO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("更新字典")
    @ResponseBody
    @RequestMapping(value = "/updateDictionary", method = RequestMethod.POST)
    public ResultVO updateDictionary(@RequestBody DictionaryPO dictionaryPO) {
        try {
            dictionaryService.updateDictionary(dictionaryPO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("删除字典列表")
    @ResponseBody
    @RequestMapping(value = "/deleteDictionary", method = RequestMethod.POST)
    public ResultVO deleteDictionary(@RequestBody IdDTO idDTO) {
        try {
            dictionaryService.deleteDictionary(idDTO.getId());
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }
}
