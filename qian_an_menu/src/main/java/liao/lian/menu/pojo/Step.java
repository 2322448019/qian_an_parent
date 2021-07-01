package liao.lian.menu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "step")
public class Step implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//步骤id
    private String pcontent;//步骤内容
    private String pic;//步骤图片
    private int menuid;//菜谱id
}
