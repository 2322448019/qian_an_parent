package liao.lian.center.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "friendlies")
public class Friendlies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userm;//用户账号
    private String usery;//用户好友账号
    private Date createdate;//创建时间
}
