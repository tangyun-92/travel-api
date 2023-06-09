package com.tang.travel.controller.scenery;

import com.tang.travel.model.pojo.SceneryCategory;
import com.tang.travel.service.scenery.SceneryCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/scenery/category")
public class SceneryCategoryController {

    @Resource
    private SceneryCategoryService sceneryCategoryService;

    @GetMapping("/list")
    public List<SceneryCategory> list() {
        return sceneryCategoryService.list();
    }

}
