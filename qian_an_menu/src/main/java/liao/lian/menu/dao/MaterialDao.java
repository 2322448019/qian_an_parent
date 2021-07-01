package liao.lian.menu.dao;

import liao.lian.menu.pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialDao extends JpaRepository<Material,Integer> {

    List<Material> findAllByMenuid(int menuid);
}
