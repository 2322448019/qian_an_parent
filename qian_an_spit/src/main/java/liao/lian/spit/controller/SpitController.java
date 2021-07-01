package liao.lian.spit.controller;

import entity.Result;
import liao.lian.spit.pojo.Spit;
import liao.lian.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @PostMapping("publish")
    private Result publish(@RequestBody Spit spit){
        return spitService.publish(spit);
    }

    @PostMapping("getAll")
    private Result getAll(@RequestBody Map map){
        return spitService.findAllByMenuid(map);
    }
}
