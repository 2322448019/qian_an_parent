package liao.lian.spit.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.spit.dao.SpitDao;
import liao.lian.spit.pojo.Spit;
import liao.lian.spit.pojo.SpitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    public Result publish(Spit spit) {
        spitDao.save(spit);
        Map map = new HashMap();
        map.put("menuid",spit.getMenuid());
        Result result= findAllByMenuid(map);
        List<Spit> spits = (List<Spit>) result.getData();
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),spits);
    }

    public Result findAllByMenuid(Map map){
        List<Spit> spits = spitDao.findAllByMenuidAndParentid((Integer) map.get("menuid"),null);
        List<SpitInfo> spitInfos = new ArrayList<>();
        for (Spit spit : spits) {
            List<Spit> spitSons = spitDao.findAllByParentid(spit.get_id());
            SpitInfo spitInfo = new SpitInfo();
            spitInfo.setSpit(spit);
            spitInfo.setSpitSons(spitSons);
            spitInfos.add(spitInfo);
        }
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),spitInfos);
    }
}
