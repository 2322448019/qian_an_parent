package liao.lian.search.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.search.dao.MenuDao;
import liao.lian.search.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public Result cookSearch(Map map) {
        Pageable pageable = PageRequest.of(0, (Integer) map.get("num"));
        List<Menu> menus = menuDao.findAllByNameLike(pageable,(String) map.get("keyword"));
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menus);
    }
}
