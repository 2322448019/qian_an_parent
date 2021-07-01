package liao.lian.center.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import liao.lian.center.pojo.Messages;
import liao.lian.center.service.MesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("mes")
@CrossOrigin
@RestController
public class MesController {

    @Autowired
    private MesService mesService;

    /**
     * 保存信息
     * @param map
     * @return
     */
    @PostMapping("saveMessage")
    public Result saveMessage(@RequestBody Map map) {
        return mesService.saveMessage(map);
    }

    /**
     * 加载更多信息
     * @param map
     * @return
     */
    @PostMapping("loadMoreMessages")
    public Result loadMoreMessages(@RequestBody Map map) {
        return mesService.loadMoreMessages(map);
    }

    /**
     * 获取系统消息
     * @param map
     * @return
     */
    @PostMapping("getSystemMessages")
    public Result getSystemMessages(@RequestBody Map map) {
        Page<Messages> pageList = mesService.getSystemMessages(map);
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),
                new PageResult<Messages>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 设置信息读取状态
     * @param map
     * @return
     */
    @PostMapping("setReadStatus")
    public Result setReadStatus(@RequestBody Map map){
        return mesService.setReadStatus(map);
    }

    /**
     * 设置操作状态，0为为操作，1为同意，2为拒绝
     * @param map
     * @return
     */
    @PostMapping("setMessageStatus")
    public Result setMessageStatus(@RequestBody Map map){
        return mesService.setMessageStatus(map);
    }

    /**
     * 删除信息
     * @param map
     * @return
     */
    @PostMapping("removeMessage")
    public Result removeMessage(@RequestBody Map map){
        return mesService.removeMessage(map);
    }
}
