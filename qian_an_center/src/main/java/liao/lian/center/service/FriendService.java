package liao.lian.center.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.center.dao.ConversationsDao;
import liao.lian.center.dao.FriendDao;
import liao.lian.center.dao.MesDao;
import liao.lian.center.dao.UserDao;
import liao.lian.center.pojo.Conversations;
import liao.lian.center.pojo.Friendlies;
import liao.lian.center.pojo.Messages;
import liao.lian.center.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MesDao mesDao;

    @Autowired
    private ConversationsDao conversationsDao;

    @Autowired
    private UserService userService;


    public Result findMyFriends(Map map) {
        //通过账号查找好友关系
        List<Friendlies> friendlies = friendDao.findAllByUserm((String) map.get("userM"));
        List<Users> usersList = new ArrayList<>();
        //获取好友关系的账号，通过该账号查询用户的信息保存到集合里
        for (Friendlies friendly : friendlies) {
            Users user = userDao.findByAccount(friendly.getUsery());
            usersList.add(user);
        }
        //将数据返回到前端
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),usersList);
    }

    public Result checkMyFriends(Friendlies friendlies) {
        Friendlies friend = friendDao.findByUsermAndUsery(friendlies.getUserm(), friendlies.getUsery());
        if (friend != null){
            return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),true);
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),false);
    }

    public Result addFriend(Map map) {
        Friendlies friend = new Friendlies();
        friend.setUserm((String) map.get("userm"));
        friend.setUsery((String) map.get("usery"));
        friend.setCreatedate(new Date());
        friendDao.save(friend);
        Friendlies friend2 = new Friendlies();
        friend2.setUserm((String) map.get("usery"));
        friend2.setUsery((String) map.get("userm"));
        friend2.setCreatedate(new Date());
        friendDao.save(friend2);
        Messages message = mesDao.getOne((Integer) map.get("id"));
        message.setStatus("1");
        mesDao.save(message);
        Messages message2 = new Messages();
        Conversations vchat = conversationsDao.findByAccountmAndAccounty("vchat", (String) map.get("userm"));
        message2.setRoomid(vchat.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        message2.setTime(time);
        message2.setAvatar((String) map.get("useryavatar"));
        message2.setUseryavatar((String) map.get("avatar"));
        message2.setMes((String) map.get("userynickname")+"同意了你的好友请求！");
        message2.setState("friend");
        message2.setType("info");
        message2.setStatus("1");
        mesDao.save(message2);
        Map meMap = new HashMap();
        meMap.put("name",map.get("userynickname"));
        meMap.put("avatar",map.get("useryavatar"));
        meMap.put("type",2);
        meMap.put("accountm",map.get("userm"));
        meMap.put("accounty",map.get("usery"));
        Map friendMap = new HashMap();
        userService.addConversitionList(meMap);

        friendMap.put("name",map.get("nickname"));
        friendMap.put("avatar",map.get("avatar"));
        friendMap.put("type",2);
        friendMap.put("accountm",map.get("usery"));
        friendMap.put("accounty",map.get("userm"));
        userService.addConversitionList(friendMap);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),message2);
    }
}
