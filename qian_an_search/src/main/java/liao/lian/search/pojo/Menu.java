package liao.lian.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Data
@Document(indexName = "qian_an_menu", type = "menu")
public class Menu implements Serializable {

    @Id
    private int id;
    private String cover;
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String name;
    private String introduction;
    private String tip;
    private String account;

}
