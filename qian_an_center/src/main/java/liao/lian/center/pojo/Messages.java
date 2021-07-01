package liao.lian.center.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "message")
public class Messages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//消息id
    private int roomid;//房间id
    private String time;//时间
    private String avatar;//用户头像
    private String mes;//消息
    private int isread;//是否已读,1为读，2为未读
    private String signature;//个性签名
    private String emoji;//表情地址
    private String style;//消息类型 emoji/mess/image
    private int groupid;//群聊id
    private String groupname;//群聊名称
    private String groupphoto;//群聊图片
    private String userm;//用户账号
    private String usery;//用户好友账号
    private String userynickname;//用户好友名称
    private String useryavatar;//用户好友头像
    private int friendroom;//用户好友房间id
    private String state;//类型 group/friend
    private String type;//validate
    private String status;//0未操作 1 同意 2 拒绝
    private String nickname;//用户昵称
}
