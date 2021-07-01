package liao.lian.center.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.center.dao.ConversationsDao;
import liao.lian.center.dao.CoverDao;
import liao.lian.center.dao.FriendDao;
import liao.lian.center.dao.UserDao;
import liao.lian.center.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.JwtUtil;

import java.util.*;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ConversationsDao conversationsDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private CoverDao coverDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Result signUp(Map signMap) {
        String account = (String) signMap.get("account");
        String password = (String) signMap.get("password");
        Users user = userDao.findByAccount(account);
        if (user != null) {
            return new Result(true, StatusCode.ACCOUNTREPE.getCode(),
                    StatusCode.ACCOUNTREPE.getMsg());
        }
        Users users = new Users();
        users.setAccount(account);
        String newpassword = encoder.encode(password);
        users.setPassword(newpassword);
        userDao.save(users);
        Friendlies friendlies = new Friendlies();
        friendlies.setUserm(account);
        friendlies.setUsery(account);
        friendlies.setCreatedate(new Date());
        friendDao.save(friendlies);
        Conversations conversation = new Conversations();
        conversation.setName("Vchat");
        conversation.setPhoto("http://192.168.11.129:8888/group1/M00/00/00/wKgLgV9jX4GATTw4AAAZbyjPJTM335.png");
        conversation.setType(0);
        conversation.setAccountm("vchat");
        conversation.setAccounty(account);
        conversationsDao.save(conversation);
        return new Result(true, StatusCode.OK.getCode(), "注册成功", account);
    }

    public Result login(Map loginMap) {
        String account = (String) loginMap.get("account");
        String password = (String) loginMap.get("password");
        Users user = userDao.findByAccount(account);
//        if (user != null && encoder.matches(password,user.getPassword())) {
//            String token = jwtUtil.createJWT(String.valueOf(user.getId()),user.getAccount(),"user");
//            Map map = new HashMap();
//            map.put("token",token);
//            map.put("user",user);
//            return new Result(true, StatusCode.OK.getCode(), "登录成功", map);
//        }
//        return new Result(true, StatusCode.LOGINERROR.getCode(), "账号不存在或密码错误");
        return new Result(true, StatusCode.OK.getCode(), "登录成功");
    }

    public Result getUserInfo(Map userMap) {
        Users user = userDao.findByAccount((String) userMap.get("account"));
        List<Conversations> conversationsList = conversationsDao.findAllByAccountm(user.getAccount());
        Conversations vchat = conversationsDao.findByAccountmAndAccounty("Vchat", user.getAccount());
        conversationsList.add(vchat);
        List<Cover> coverList = coverDao.findAllByAccount(user.getAccount());
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setConversationsList(conversationsList);
        userInfo.setCoverList(coverList);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), userInfo);
    }

    public Result loginOut() {
        return new Result(true, StatusCode.OK.getCode(), "退出成功");
    }

    public Result upUserInfo(Users userData) {
        Users user = userDao.findByAccount(userData.getAccount());
        if (userData.getId() != 0) {
            user.setId(userData.getId());
        }
        if (userData.getSignature() != null) {
            user.setSignature(userData.getSignature());
        }
        if (userData.getNickname() != null) {
            user.setNickname(userData.getNickname());
        }
        if (userData.getEmail() != null) {
            user.setEmail(userData.getEmail());
        }
        if (userData.getPhone() != null) {
            user.setPhone(userData.getPhone());
        }
        if (userData.getSex() != null) {
            user.setSex(userData.getSex());
        }
        if (userData.getProvince() != null) {
            user.setProvince(userData.getProvince());
        }
        if (userData.getCity() != null) {
            user.setCity(userData.getCity());
        }
        if (userData.getTown() != null) {
            user.setTown(userData.getTown());
        }
        if (userData.getWallpaper() != null) {
            user.setWallpaper(userData.getWallpaper());
        }
        if (userData.getAvatar() != null) {
            user.setAvatar(userData.getAvatar());
        }
        userDao.save(user);
        return new Result(true, StatusCode.OK.getCode(), "保存成功");
    }

    public Result getUserDetail(Map map) {
        Users user = userDao.findByAccount((String) map.get("account"));
        return new Result(true, StatusCode.OK.getCode(), "查询成功", user);
    }

    public Result huntFriends(Map map) {
        Users user = userDao.findByAccount((String) map.get("key"));
        List<Users> friendList = new ArrayList<>();
        friendList.add(user);
        return new Result(true, StatusCode.OK.getCode(), "查询成功", friendList);
    }

    public Result addConversitionList(Map map) {
        Conversations conversation = new Conversations();
        conversation.setName((String) map.get("name"));
        conversation.setAccountm((String) map.get("accountm"));
        conversation.setAccounty((String) map.get("accounty"));
        conversation.setPhoto((String) map.get("avatar"));
        conversation.setType((Integer) map.get("type"));
        conversationsDao.save(conversation);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    public Result removeConversitionList(Map map) {
        conversationsDao.removeByAccountmAndAccounty((String) map.get("accountm"),
                (String) map.get("accounty"));
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

}
