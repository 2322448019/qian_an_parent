package liao.lian.spit.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SpitInfo implements Serializable {

    Spit spit;
    List<Spit> spitSons;
}
