package liao.lian.center.dao;

import liao.lian.center.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository支持接口规范方法名查询。意思是如果在接口中定义的查询方法符合它的命名规则，就可以不用写实现
 * JpaSpecificationExecutor接口，只要简单实现toPredicate方法就可以实现复杂的查询
 */
public interface UserDao extends JpaRepository<Users,Integer>, JpaSpecificationExecutor<Users> {

    /**
     * 通过账号查询用户
     * @param account
     * @return
     */
    Users findByAccount(String account);

}
