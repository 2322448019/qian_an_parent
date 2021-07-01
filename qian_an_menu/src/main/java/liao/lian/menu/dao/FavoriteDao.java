package liao.lian.menu.dao;

import liao.lian.menu.pojo.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDao extends JpaRepository<Favorite,Integer> {

    List<Favorite> findAllByAccount(String account);

}
