package liao.lian.center.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo {
    private Users user;
    private List<Conversations> conversationsList;
    private List<Cover> coverList;
}
