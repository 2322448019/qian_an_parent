package liao.lian.center.controller;

import entity.Result;
import liao.lian.center.pojo.Conversations;
import liao.lian.center.pojo.Users;
import liao.lian.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户的信息
     * @param userMap
     * @return
     */
    @PostMapping("getUserInfo")
    public Result getUserInfo(@RequestBody Map userMap) {
        return userService.getUserInfo(userMap);
    }

    /**
     * 用户注册
     * @param signMap
     * @return
     */
    @PostMapping("signUp")
    public Result signUp(@RequestBody Map signMap) {
        return userService.signUp(signMap);
    }

    /**
     * 用户登录
     * @param loginMap
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Map loginMap) {
        return userService.login(loginMap);
    }

    /**
     * 用户退出
     * @return
     */
    @PostMapping("loginOut")
    public Result loginOut() {
        return userService.loginOut();
    }

    /**
     * 更新用户信息
     * @param userData
     * @return
     */
    @PostMapping("upUserInfo")
    public Result upUserInfo(@RequestBody Users userData) {
        return userService.upUserInfo(userData);
    }

    /**
     * 获取用户详细信息
     * @param map
     * @return
     */
    @PostMapping("getUserDetail")
    public Result getUserDetail(@RequestBody Map map) {
        return userService.getUserDetail(map);
    }

    /**
     * 搜索用户
     * @param map
     * @return
     */
    @PostMapping("huntFriends")
    public Result huntFriends(@RequestBody Map map) {
        return userService.huntFriends(map);
    }

    /**
     * 添加到会话列表
     * @param map
     * @return
     */
    @PostMapping("addConversitionList")
    public Result addConversitionList(@RequestBody Map map) {
        return userService.addConversitionList(map);
    }

    /**
     * 从会话列表里删除
     * @param map
     * @return
     */
    @PostMapping("removeConversitionList")
    public Result removeConversitionList(@RequestBody Map map){
        return userService.removeConversitionList(map);
    }

}
