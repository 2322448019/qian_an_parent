package liao.lian.menu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuDetail implements Serializable {
    private Menu menu;
    private List<Step> steps;
    private List<Material> materials;
}
