package liao.lian.menu.dao;

import liao.lian.menu.pojo.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepDao extends JpaRepository<Step,Integer> {

    List<Step> findAllByMenuid(int menuid);
}
