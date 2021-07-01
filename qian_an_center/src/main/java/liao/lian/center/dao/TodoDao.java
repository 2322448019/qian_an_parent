package liao.lian.center.dao;

import liao.lian.center.pojo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoDao extends JpaRepository<Todo,Integer> {

    /**
     * 查询该用户所有的日程安排
     * @param account
     * @return
     */
    List<Todo> findAllByAccount(String account);
}
