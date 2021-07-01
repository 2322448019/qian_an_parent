package liao.lian.center.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
 * @Entity 表明该类为一个实体类
 * @Table 对应的表的名称
 * Serializable接口是启用其序列化功能的接口。实现java.io.Serializable 接口的类是可序列化的
 */
@Data
@Entity
@Table(name = "conversations")
public class Conversations implements Serializable {

    /**
     * @Id 标注用于声明一个实体类的属性映射为数据库的主键列
     * IDENTITY：主键由数据库自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //会话id
    private String name;//会话名称
    private String photo;//会话头像
    private int type;//会话类型，1是群聊，2是好友
    private String accountm;//我的用户账号
    private String accounty;//好友的用户账号
}
