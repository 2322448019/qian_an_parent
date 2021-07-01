package liao.lian.spit.dao;

import liao.lian.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpitDao extends MongoRepository<Spit,String> {

    List<Spit> findAllByMenuidAndParentid(int menuid,String parentid);

    List<Spit> findAllByParentid(String id);
}
