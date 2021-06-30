package com.zss98.AuthorityResolving.Controller;

import com.zss98.AuthorityResolving.Entity.CommonResult;
import com.zss98.AuthorityResolving.Entity.RespEnum;
import com.zss98.AuthorityResolving.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService service;

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        return RespEnum.OK.result(service.getAllUser());
    }
}
