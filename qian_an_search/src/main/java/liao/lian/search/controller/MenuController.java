package liao.lian.search.controller;

import entity.Result;
import liao.lian.search.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("cookbook")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("cookSearch")
    private Result cookSearch(@RequestBody Map map){
        return menuService.cookSearch(map);
    }
}
