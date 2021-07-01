package liao.lian.center.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.center.dao.ConversationsDao;
import liao.lian.center.dao.GroupDao;
import liao.lian.center.dao.GroupUserDao;
import liao.lian.center.dao.MesDao;
import liao.lian.center.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupUserDao groupUserDao;

    @Autowired
    private ConversationsDao conversationsDao;

    @Autowired
    private MesDao mesDao;

    @Autowired
    private UserService userService;

    public Result getMyGroup(Map map){
        List<GroupUser> groupUserList = groupUserDao.findAllByAccount((String) map.get("account"));
        List<Groups> groupsList = new ArrayList<>();
        Groups group;
        for (GroupUser groupUser : groupUserList) {
            group = groupDao.getOne(groupUser.getGroupid());
            groupsList.add(group);
        }
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),groupsList);
    }

    public Result huntGroups(Map map) {
        Groups group = groupDao.findByTitle((String) map.get("key"));
        List<Groups> groupsList = new ArrayList<>();
        groupsList.add(group);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),groupsList);
    }

    public Result createGroup(Map map) {
        Groups group = new Groups();
        group.setCreatedate(new Date(System.currentTimeMillis()));
        group.setTitle((String) map.get("groupName"));
        group.setDescription((String) map.get("groupDesc"));
        group.setHolderaccount((String) map.get("holderAccount"));
        group.setUsernum(1);
        group.setGrades(1);
        group.setImg((String) map.get("groupImage"));
        Groups save = groupDao.save(group);
        Groups group1 = groupDao.findByTitle(save.getTitle());
        GroupUser groupUser = new GroupUser();
        groupUser.setAccount(save.getHolderaccount());
        groupUser.setHolder(save.getHolderaccount());
        groupUser.setGroupid(group1.getId());
        groupUser.setUsername(save.getHolderaccount());
        groupUser.setCard(save.getDescription());
        groupUser.setAvatar((String) map.get("avatar"));
        groupUser.setManager((String) map.get("holderAccount"));
        groupUserDao.save(groupUser);
        Conversations conversation = new Conversations();
        conversation.setName((String) map.get("groupName"));
        conversation.setPhoto((String) map.get("groupImage"));
        conversation.setType(1);
        conversation.setAccountm((String) map.get("holderAccount"));
        conversation.setAccounty((String) map.get("holderAccount"));
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),map.get("holderAccount"));
    }

    public Result getGroupInfo(Map map) {
        Groups group = groupDao.getOne((Integer) map.get("groupId"));
        List<GroupUser> groupUserList = groupUserDao.findAllByGroupid(group.getId());
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroup(group);
        groupInfo.setGroupUserList(groupUserList);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),groupInfo);
    }

    public Result getGroupUsers(Map map) {
        List<GroupUser> groupUserList = new ArrayList<>();
        if (map.get("groupId") != null){
            groupUserList = groupUserDao.findAllByGroupid((Integer) map.get("groupId"));
        }else if ( map.get("roomid") != null){
            Conversations conversation = conversationsDao.getOne((Integer) map.get("roomid"));
            String title = conversation.getName();
            Groups group = groupDao.findByTitle(title);
            groupUserList = groupUserDao.findAllByGroupid(group.getId());
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),groupUserList);
    }

    public Result addGroupUsers(Map map) {
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupid((Integer) map.get("groupid"));
        groupUser.setUsername((String) map.get("nickname"));
        groupUser.setManager((String) map.get("usery"));
        groupUser.setHolder((String) map.get("usery"));
        groupUser.setCard((String) map.get("signature"));
        groupUser.setAccount((String) map.get("userm"));
        groupUser.setAvatar((String) map.get("avatar"));
        groupUserDao.save(groupUser);
        Messages message = mesDao.getOne((Integer) map.get("id"));
        message.setStatus("1");
        mesDao.save(message);
        Messages message2 = new Messages();
        Conversations vchat = conversationsDao.findByAccountmAndAccounty("vchat", (String) map.get("userm"));
        message2.setRoomid(vchat.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new java.util.Date());
        message2.setTime(time);
        message2.setAvatar((String) map.get("useryavatar"));
        message2.setUseryavatar((String) map.get("avatar"));
        message2.setMes("管理员已同意加入该群："+(String) map.get("groupname"));
        message2.setState("friend");
        message2.setType("info");
        message2.setStatus("1");
        mesDao.save(message2);
        mesDao.save(message2);
        Map meMap = new HashMap();
        meMap.put("name",map.get("groupname"));
        Groups group = groupDao.findByTitle((String) map.get("groupname"));
        meMap.put("avatar",group.getImg());
        meMap.put("type",1);
        meMap.put("accountm",map.get("userm"));
        meMap.put("accounty",map.get("usery"));
        userService.addConversitionList(meMap);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),groupUser);
    }
}
