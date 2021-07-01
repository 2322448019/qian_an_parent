package liao.lian.center.controller;

import entity.Result;
import liao.lian.center.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 获取群聊列表
     * @param map
     * @return
     */
    @PostMapping("getMyGroup")
    public Result getMyGroup(@RequestBody Map map){
        return groupService.getMyGroup(map);
    }

    /**
     * 搜索群聊
     * @param map
     * @return
     */
    @PostMapping("huntGroups")
    public Result huntGroups(@RequestBody Map map){
        return groupService.huntGroups(map);
    }

    /**
     * 创建群聊
     * @param map
     * @return
     */
    @PostMapping("createGroup")
    public Result createGroup(@RequestBody Map map){
        return groupService.createGroup(map);
    }

    /**
     * 获取群聊信息
     * @param map
     * @return
     */
    @PostMapping("getGroupInfo")
    public Result getGroupInfo(@RequestBody Map map){
        return groupService.getGroupInfo(map);
    }

    /**
     * 获取群聊成员
     * @param map
     * @return
     */
    @PostMapping("getGroupUsers")
    public Result getGroupUsers(@RequestBody Map map){
        return groupService.getGroupUsers(map);
    }

    /**
     * 同意加入群聊
     * @param map
     * @return
     */
    @PostMapping("addGroup")
    public Result addGroupUsers(@RequestBody Map map){
        return groupService.addGroupUsers(map);
    }
}
