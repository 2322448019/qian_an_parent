package liao.lian.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class Spit implements Serializable {

    @Id
    private String _id;
    private String content;
    private String publishtime;
    private String account;
    private String avatar;
    private String nickname;
    private String replaynickname;
    private String parentid;
    private int menuid;
}
