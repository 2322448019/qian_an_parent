package liao.lian.center.dao;

import liao.lian.center.pojo.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<Groups,Integer> {

    /**
     * 通过群名查找群
     * @param title
     * @return
     */
    Groups findByTitle(String title);

}
