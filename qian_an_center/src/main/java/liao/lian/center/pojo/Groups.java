package liao.lian.center.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "groups")
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//群聊id
    private String title;//名称
    private String description;//简介
    private String img;//群头像
    private Date createdate;//创建时间
    private int usernum;//成员数
    private int grades;//等级
    private String holderaccount;//群主账号
}
