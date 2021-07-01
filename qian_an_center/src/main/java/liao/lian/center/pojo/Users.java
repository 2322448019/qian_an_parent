package liao.lian.center.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//用户id
    private String account;//账号
    private String password;//密码
    private String avatar;//头像
    private String signature;//个性签名
    private String nickname;//用户名称
    private String email;//邮件
    private String phone;//手机
    private String sex;//性别，1为男，2为女，3为保密
    private String wallpaper;//聊天壁纸
    private Timestamp signuptime;//注册时间
    private Timestamp lastlogintime;//最后一次登录
    private String province;//省
    private String city;//市
    private String town;//区
}
