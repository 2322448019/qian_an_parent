package liao.lian.menu.dao;

import liao.lian.menu.pojo.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuCategoryDao extends JpaRepository<MenuCategory,Integer>, JpaSpecificationExecutor<MenuCategory> {
}
