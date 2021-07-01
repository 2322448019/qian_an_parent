package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    // 总页数
    private Long totalPage;
    // 查询的数据
    private List<T> data;

    /**
     * 控构造器
     */
    public PageResult() {
    }

    public PageResult(Long totalPage, List<T> data) {
        this.totalPage = totalPage;
        this.data = data;
    }
}
