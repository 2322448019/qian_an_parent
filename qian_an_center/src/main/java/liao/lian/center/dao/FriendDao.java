package liao.lian.center.dao;

import liao.lian.center.pojo.Friendlies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendDao extends JpaRepository<Friendlies,Integer> {

    /**
     * 查询该用户的所有好友
     * @param userm
     * @return
     */
    List<Friendlies> findAllByUserm(String userm);

    /**
     * 检查该用户是否是你的好友
     * @param userm
     * @param usery
     * @return
     */
    Friendlies findByUsermAndUsery(String userm, String usery);
}
