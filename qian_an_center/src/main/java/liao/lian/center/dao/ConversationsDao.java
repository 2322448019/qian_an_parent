package liao.lian.center.dao;

import liao.lian.center.pojo.Conversations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConversationsDao extends JpaRepository<Conversations,Integer>,
        JpaSpecificationExecutor<Conversations> {

    /**
     * 通过用户账号搜索所有会话列表
     * @param accountm
     * @return
     */
    List<Conversations> findAllByAccountm(String accountm);

    /**
     * 通过本人账号和好友账号搜索对应会话
     * @param vchat
     * @param accountm
     * @return
     */
    Conversations findByAccountmAndAccounty(String vchat,String accountm);

    /**
     * 删除会话
     * @param accountm
     * @param accounty
     */
    void removeByAccountmAndAccounty(String accountm, String accounty);

    /**
     * 通过群名称搜索该群的所有会话
     * @param title
     * @return
     */
    List<Conversations> findAllByName(String title);

}
