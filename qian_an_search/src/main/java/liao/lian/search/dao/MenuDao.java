package liao.lian.search.dao;

import liao.lian.search.pojo.Menu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MenuDao extends ElasticsearchRepository<Menu,Integer> {

    List<Menu> findAllByNameLike(Pageable pageable, String name);
}
