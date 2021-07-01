package liao.lian.center.pojo;

import lombok.Data;

import java.util.List;

@Data
public class GroupInfo {

    private Groups group;
    private List<GroupUser> groupUserList;
}
