package liao.lian.menu.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import liao.lian.menu.pojo.MenuCategory;
import liao.lian.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("cookbook")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("butMenu")
    private Result butMenu(@RequestBody Map map){
        return menuService.butMenu(map);
    }

    @PostMapping("cookgreen")
    private Result cookgreen(@RequestBody Map map){
        return menuService.cookgreen(map);
    }

    @PostMapping("cookDetail")
    private Result cookDetail(@RequestBody Map map){
        return menuService.cookDetail(map);
    }

    @PostMapping("addFavorite")
    private Result addFavorite(@RequestBody Map map){
        return menuService.addFavorite(map);
    }

    @PostMapping("cookSearch")
    private Result cookSearch(@RequestBody Map map){
        return menuService.cookSearch(map);
    }

    @PostMapping("lookDishesLike")
    private Result lookDishesLike(@RequestBody Map map){
        return menuService.lookDishesLike(map);
    }

    @PostMapping("recommend")
    private Result recommend(@RequestBody Map map){
        return menuService.recommend(map);
    }

    @PostMapping("myMenu")
    private Result myMenu(@RequestBody Map map){
        return menuService.myMenu(map);
    }

    @PostMapping("loadMenu")
    private Result loadMenu(@RequestBody Map map){
        return menuService.loadMenu(map);
    }
}
