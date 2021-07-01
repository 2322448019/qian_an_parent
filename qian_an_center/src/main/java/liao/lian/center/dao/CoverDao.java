package liao.lian.center.dao;

import liao.lian.center.pojo.Cover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoverDao extends JpaRepository<Cover,Integer> {

    /**
     * 查询该用户的所有封面
     * @param account
     * @return
     */
    List<Cover> findAllByAccount(String account);
}
