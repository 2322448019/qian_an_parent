package liao.lian.menu.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.menu.dao.*;
import liao.lian.menu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private StepDao stepDao;

    @Autowired
    private MenuCategoryDao menuCategoryDao;

    @Autowired
    private FavoriteDao favoriteDao;

    @CacheEvict(value = "result", key = "'myMenu_'+#map.account")
    public Result butMenu(Map map) {
        Map cookData = (Map) map.get("cookData");
        List<Map> materialList = (List<Map>) map.get("material");
        List<Map> setpInfoList = (List<Map>) map.get("setpInfo");
        String account = (String) map.get("account");
        ArrayList categoryList = (ArrayList) map.get("categoryList");
        Menu menu = new Menu();
        if (cookData.get("name") != null){
            menu.setName((String) cookData.get("name"));
        }
        if (cookData.get("introduction") != null){
            menu.setIntroduction((String) cookData.get("introduction"));
        }
        if (cookData.get("tip") != null){
            menu.setTip((String) cookData.get("tip"));
        }
        if (cookData.get("cover") != null){
            menu.setCover((String) cookData.get("cover"));
        }
        if (account != null){
            menu.setAccount(account);
        }
        Menu save = menuDao.save(menu);
        for (Map materialMap : materialList) {
            Material material = new Material();
            if (materialMap.get("mname") != null){
                material.setMname((String) materialMap.get("mname"));
            }
            if (materialMap.get("mintro") != null){
                material.setMintro((String) materialMap.get("mintro"));
            }
            material.setMenuid(save.getId());
            materialDao.save(material);
        }
        for (Map stepInfoMap : setpInfoList) {
            Step step = new Step();
            if (stepInfoMap.get("pcontent") != null){
                step.setPcontent((String) stepInfoMap.get("pcontent"));
            }
            if (stepInfoMap.get("pic") != null){
                step.setPic((String) stepInfoMap.get("pic"));
            }
            step.setMenuid(save.getId());
            stepDao.save(step);
        }
        MenuCategory menuCategory = new MenuCategory();
        for (int i = 0; i < categoryList.size(); i++) {
            menuCategory.setMenuid(save.getId());
            menuCategory.setClassid((String) categoryList.get(i));
            menuCategoryDao.save(menuCategory);
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    @Cacheable(value = "result",key = "'cookgreen_'+#map.classid")
    public Result cookgreen(Map map) {
        Specification<MenuCategory> specification = createSpecification(map);
        PageRequest pageRequest = PageRequest.of((Integer) map.get("page"),(Integer) map.get("size"));
        List<MenuCategory> menuCategories= menuCategoryDao.findAll(specification, pageRequest).getContent();
        List<Menu> menus = new ArrayList<>();
        for (MenuCategory menuCategory : menuCategories) {
            Menu menu = menuDao.getOne(menuCategory.getMenuid());
            menus.add(menu);
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menus);
    }

    @Cacheable(value = "result", key = "'cookDetail_'+#map.id")
    public Result cookDetail(Map map) {
        MenuDetail menuDetail = new MenuDetail();
        Menu menu = menuDao.getOne((Integer) map.get("id"));
        menuDetail.setMenu(menu);
        List<Material> materials = materialDao.findAllByMenuid((Integer) map.get("id"));
        menuDetail.setMaterials(materials);
        List<Step> steps = stepDao.findAllByMenuid((Integer) map.get("id"));
        menuDetail.setSteps(steps);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menuDetail);
    }

    @CacheEvict(value = "result", key = "'lookDishesLike_'+#map.account")
    public Result addFavorite(Map map) {
        Favorite favorite = new Favorite();
        favorite.setMenuid((Integer) map.get("menuid"));
        favorite.setAccount((String) map.get("account"));
        favoriteDao.save(favorite);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    public Result cookSearch(Map map) {
        Pageable pageable = PageRequest.of(0, (Integer) map.get("num"));
        List<Menu> menus = menuDao.findByNameLike(pageable, "%"+(String) map.get("keyword")+"%");
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menus);
    }

    private Specification<MenuCategory> createSpecification(Map map){
        return new Specification<MenuCategory>() {
            @Override
            public Predicate toPredicate(Root<MenuCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (map.get("classid") != null){
                    predicateList.add(criteriaBuilder.equal(root.get("classid").as(Integer.class),map.get("classid")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    @Cacheable(value = "result", key = "'lookDishesLike_'+#map.account")
    public Result lookDishesLike(Map map) {
        List<Favorite> favorites = favoriteDao.findAllByAccount((String) map.get("account"));
        List<Menu> menus = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Menu menu = menuDao.getOne(favorite.getMenuid());
            menus.add(menu);
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menus);
    }

    public Result recommend(Map map) {
        List<Menu> menus = menuDao.recommend((Integer) map.get("size"));
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menus);
    }

    @Cacheable(value = "result", key = "'myMenu_'+#map.account")
    public Result myMenu(Map map) {
        List<Menu> menus = menuDao.findAllByAccount((String) map.get("account"));
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menus);
    }

    public Result loadMenu(Map map) {
        Menu menu = menuDao.findByIdAndAccount((Integer) map.get("menuid"), (String) map.get("account"));
        if (menu == null){
            return new Result(true,StatusCode.ERROR.getCode(),StatusCode.OK.getMsg());
        }
        List<Material> materials = materialDao.findAllByMenuid((Integer) map.get("menuid"));
        List<Step> steps = stepDao.findAllByMenuid((Integer) map.get("menuid"));
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setMenu(menu);
        menuDetail.setMaterials(materials);
        menuDetail.setSteps(steps);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),menuDetail);
    }
}
