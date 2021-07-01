package liao.lian.center.dao;

import liao.lian.center.pojo.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUserDao extends JpaRepository<GroupUser,Integer> {

    /**
     *查询该用户所在的所有群
     * @param account
     * @return
     */
    List<GroupUser> findAllByAccount(String account);

    /**
     * 通过群id查询该群的所有成员
     * @param groupid
     * @return
     */
    List<GroupUser> findAllByGroupid(Integer groupid);
}
