package liao.lian.center.controller;

import entity.Result;
import liao.lian.center.pojo.Friendlies;
import liao.lian.center.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 查找好友列表
     * @param map
     * @return
     */
    @PostMapping("findMyFriends")
    public Result findMyFriends(@RequestBody Map map){
        return friendService.findMyFriends(map);
    }

    /**
     * 检查是否为好友
     * @param friendlies
     * @return
     */
    @PostMapping("checkMyFriends")
    public Result checkMyFriends(@RequestBody Friendlies friendlies){
        return friendService.checkMyFriends(friendlies);
    }

    /**
     * 添加好友
     * @param map
     * @return
     */
    @PostMapping("addFriend")
    public Result addFriend(@RequestBody Map map){
        return friendService.addFriend(map);
    }
}
