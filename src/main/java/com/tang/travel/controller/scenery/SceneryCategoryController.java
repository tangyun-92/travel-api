package com.tang.travel.controller.scenery;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.service.scenery.SceneryCategoryService;
import com.tang.travel.util.PageBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/scenery/category")
public class SceneryCategoryController {

    @Resource
    private SceneryCategoryService sceneryCategoryService;

    @GetMapping("/list")
    public ApiRestResponse list() {

        PageBean list = sceneryCategoryService.list();
        return ApiRestResponse.success(list);

    }

}
