package liao.lian.menu.dao;

import liao.lian.menu.pojo.Menu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu,Integer>, JpaSpecificationExecutor<Menu> {

    List<Menu> findByNameLike(Pageable pageable, String name);

    @Modifying
    @Query(value = "select * from menu order by RAND() limit ?", nativeQuery = true)
    List<Menu> recommend(int size);

    List<Menu> findAllByAccount(String account);

    Menu findByIdAndAccount(int id,String account);
}
