package com.zss98.AuthorityResolving.Controller;

import com.zss98.AuthorityResolving.Entity.CommonResult;
import com.zss98.AuthorityResolving.Entity.RespEnum;
import com.zss98.AuthorityResolving.Service.ResService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/res")
public class ResController {

    private ResService service;

    public ResController(ResService service){
        this.service = service;
    }

    @RequestMapping("/all")
    public CommonResult getAll(){
        return RespEnum.OK.result(service.getAll());
    }
}
