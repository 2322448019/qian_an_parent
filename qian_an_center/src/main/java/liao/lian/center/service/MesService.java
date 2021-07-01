package liao.lian.center.service;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import liao.lian.center.dao.ConversationsDao;
import liao.lian.center.dao.GroupDao;
import liao.lian.center.dao.GroupUserDao;
import liao.lian.center.dao.MesDao;
import liao.lian.center.pojo.Conversations;
import liao.lian.center.pojo.GroupUser;
import liao.lian.center.pojo.Groups;
import liao.lian.center.pojo.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MesService {

    @Autowired
    private MesDao mesDao;

    @Autowired
    private ConversationsDao conversationsDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupUserDao groupUserDao;

    public Result loadMoreMessages(Map map) {
        PageRequest pageRequest = PageRequest.of((Integer) map.get("offset")-1, (Integer) map.get("limit"));
        List<Messages> messages = mesDao.findAllByRoomid(pageRequest,(Integer) map.get("roomid"));
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),messages);
    }

    public Result saveMessage(Map map) {
        Conversations conversations = conversationsDao.getOne((Integer) map.get("roomid"));
        List<Messages> messagesList = new ArrayList<>();
        if (conversations.getType() == 2 || conversations.getType() == 0){
            List<Integer> friendRoom = new ArrayList();
            friendRoom.add((Integer) map.get("roomid"));
            String accountm = conversations.getAccountm();
            String accounty = conversations.getAccounty();
            Conversations conversations2 = conversationsDao.findByAccountmAndAccounty(accounty, accountm);
            friendRoom.add(conversations2.getId());
            for (int i = 0; i < 2; i++) {
                Messages message = new Messages();
                if (map.get("roomid") != null){
                    message.setRoomid(friendRoom.get(i));
                }
                if (map.get("time") != null){
                    message.setTime((String) map.get("time"));
                }
                if ( map.get("avatar") != null){
                    message.setAvatar((String) map.get("avatar"));
                }
                if (map.get("mes") != null){
                    message.setMes((String) map.get("mes"));
                }
                if (map.get("read") != null){
                    message.setIsread((Integer) map.get("read"));
                }
                if (map.get("signature") != null){
                    message.setSignature((String) map.get("signature"));
                }
                if (map.get("emoji") != null){
                    message.setEmoji((String) map.get("emoji"));
                }
                if (map.get("style") != null){
                    message.setStyle((String) map.get("style"));
                }
                if (map.get("groupid") != null){
                    message.setGroupid((Integer) map.get("groupid"));
                }
                if (map.get("title") != null){
                    message.setGroupname((String) map.get("title"));
                }
                if (map.get("groupphoto") != null){
                    message.setGroupphoto((String) map.get("groupphoto"));
                }
                if (map.get("userM") != null){
                    message.setUserm((String) map.get("userM"));
                }
                if (map.get("userY") != null){
                    message.setUsery((String) map.get("userY"));
                }
                if (map.get("userYnickname") != null){
                    message.setUserynickname((String) map.get("userYnickname"));
                }
                if (map.get("userYavatar") != null){
                    message.setUseryavatar((String) map.get("userYavatar"));
                }
                if (map.get("friendroom") != null){
                    message.setFriendroom((Integer) map.get("friendroom"));
                }
                if (map.get("state") != null){
                    message.setState((String) map.get("state"));
                }
                if (map.get("type") != null){
                    message.setType((String) map.get("type"));
                }
                if (map.get("status") != null){
                    message.setStatus((String) map.get("status"));
                }
                if (map.get("type") != null){
                    if (map.get("type").equals("validate")){
                        Conversations vchat = conversationsDao.findByAccountmAndAccounty("vchat", (String) map.get("userY"));
                        message.setRoomid(vchat.getId());
                    }
                }
                if ( map.get("nickname") != null){
                    message.setNickname((String) map.get("nickname"));
                }
                mesDao.save(message);
                messagesList.add(message);
            }
        }

        if (conversations.getType() == 1){
            String title = conversations.getName();
            List<Conversations> conversationsList = conversationsDao.findAllByName(title);
            for (Conversations conversations1 : conversationsList) {
                Messages message = new Messages();
                message.setRoomid(conversations1.getId());
                if (map.get("time") != null){
                    message.setTime((String) map.get("time"));
                }
                if ( map.get("avatar") != null){
                    message.setAvatar((String) map.get("avatar"));
                }
                if (map.get("mes") != null){
                    message.setMes((String) map.get("mes"));
                }
                if (map.get("read") != null){
                    message.setIsread((Integer) map.get("read"));
                }
                if (map.get("signature") != null){
                    message.setSignature((String) map.get("signature"));
                }
                if (map.get("emoji") != null){
                    message.setEmoji((String) map.get("emoji"));
                }
                if (map.get("style") != null){
                    message.setStyle((String) map.get("style"));
                }
                if (map.get("groupid") != null){
                    message.setGroupid((Integer) map.get("groupid"));
                }
                if (map.get("title") != null){
                    message.setGroupname((String) map.get("title"));
                }
                if (map.get("groupphoto") != null){
                    message.setGroupphoto((String) map.get("groupphoto"));
                }
                if (map.get("userM") != null){
                    message.setUserm((String) map.get("userM"));
                }
                if (map.get("userY") != null){
                    message.setUsery((String) map.get("userY"));
                }
                if (map.get("userYnickname") != null){
                    message.setUserynickname((String) map.get("userYnickname"));
                }
                if (map.get("userYavatar") != null){
                    message.setUseryavatar((String) map.get("userYavatar"));
                }
                if (map.get("friendroom") != null){
                    message.setFriendroom((Integer) map.get("friendroom"));
                }
                if (map.get("state") != null){
                    message.setState((String) map.get("state"));
                }
                if (map.get("type") != null){
                    message.setType((String) map.get("type"));
                }
                if (map.get("status") != null){
                    message.setStatus((String) map.get("status"));
                }
                if ( map.get("nickname") != null){
                    message.setNickname((String) map.get("nickname"));
                }
                mesDao.save(message);
                messagesList.add(message);
            }
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),messagesList);
    }

    public Page<Messages> getSystemMessages(Map map) {
        Specification<Messages> specification = createSpecification(map);
        PageRequest pageRequest = PageRequest.of((Integer) map.get("offset")-1,(Integer) map.get("limit"));
        return mesDao.findAll(specification,pageRequest);
    }

    public Result setReadStatus(Map map) {
        List<Messages> messages = mesDao.findAllByRoomid((Integer) map.get("roomid"));
        for (Messages message : messages) {
            message.setIsread(1);
            mesDao.save(message);
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    public Result setMessageStatus(Map map) {
        Messages message = mesDao.getOne((Integer) map.get("id"));
        message.setStatus((String) map.get("status"));
        mesDao.save(message);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    public Result removeMessage(Map map) {
        mesDao.deleteById((Integer) map.get("id"));
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }


    private Specification<Messages> createSpecification(Map map){
        return new Specification<Messages>() {
            @Override
            public Predicate toPredicate(Root<Messages> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (map.get("roomid") != null){
                    predicateList.add(criteriaBuilder.equal(root.get("roomid").as(Integer.class),map.get("roomid")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
