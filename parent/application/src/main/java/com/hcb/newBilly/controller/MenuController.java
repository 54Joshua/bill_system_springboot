package com.hcb.newBilly.controller;

import com.hcb.newBilly.common.Result;
import com.hcb.newBilly.dto.IdDTO;
import com.hcb.newBilly.dto.MenuDTO;
import com.hcb.newBilly.po.MenuPO;
import com.hcb.newBilly.service.MenuService;
import com.hcb.newBilly.vo.MenuRelationVO;
import com.hcb.newBilly.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api("目录管理")
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("保存目录")
    @ResponseBody
    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
    public ResultVO saveMenu(@RequestBody MenuDTO menuDTO) {
        try {
            menuService.saveMenu(menuDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("更新目录")
    @ResponseBody
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public ResultVO updateMenu(@RequestBody MenuDTO menuDTO) {
        try {
            menuService.updateMenu(menuDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("删除目录")
    @ResponseBody
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    public ResultVO deleteMenu(@RequestBody IdDTO idDTO) {
        try {
            menuService.deleteMenu(idDTO.getId());
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("查找目录")
    @ResponseBody
    @RequestMapping(value = "/findMenu", method = RequestMethod.POST)
    public ResultVO findMenu() {
        try {
            List<MenuRelationVO> menuRelationVOList = menuService.findMenu();
            return Result.ok(menuRelationVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("查找目录List")
    @ResponseBody
    @RequestMapping(value = "/findMenuPOs", method = RequestMethod.POST)
    public ResultVO findMenuPOs() {
        try {
            List<MenuPO> menuPOS = menuService.findMenuPOS();
            return Result.ok(menuPOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());

        }
    }
}
