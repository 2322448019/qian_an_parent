package liao.lian.chat.client;

import entity.Result;
import liao.lian.chat.client.impl.MesClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "qian-an-center", fallback = MesClientImpl.class)
public interface MesClient {

    @PostMapping("mes/saveMessage")
    public Result saveMessage(@RequestBody Map map);

    @PostMapping("mes/getSystemMessages")
    public Result getSystemMessages(@RequestBody Map map);

    @PostMapping("mes/setReadStatus")
    public Result setReadStatus(@RequestBody Map map);

    @PostMapping("mes/setMessageStatus")
    public Result setMessageStatus(@RequestBody Map map);

    @PostMapping("friend/addFriend")
    public Result addFriend(@RequestBody Map map);

    @PostMapping("group/addGroup")
    public Result addGroupUsers(@RequestBody Map map);

    @PostMapping("mes/loadMoreMessages")
    public Result loadMoreMessages(@RequestBody Map map);
}
