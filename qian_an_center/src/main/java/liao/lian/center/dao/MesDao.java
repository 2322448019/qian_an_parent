package liao.lian.center.dao;

import liao.lian.center.pojo.Messages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MesDao extends JpaRepository<Messages,Integer>, JpaSpecificationExecutor<Messages> {

    /**
     * 查询该会话的所有信息
     * @param roomid
     * @return
     */
    List<Messages> findAllByRoomid(Integer roomid);

    /**
     * 查询该会话一定数量的消息
     * @param pageable
     * @param roomid
     * @return
     */
    List<Messages> findAllByRoomid(Pageable pageable,Integer roomid);


}
