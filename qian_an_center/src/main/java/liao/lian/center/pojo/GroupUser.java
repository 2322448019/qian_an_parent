package liao.lian.center.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "groupuser")
public class GroupUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//id
    private int groupid;//群聊id
    private String username;//用户名
    private String manager;//管理员
    private String holder;//群主
    private String card;//群名片
    private String account;//用户账号
    private String avatar;
}
