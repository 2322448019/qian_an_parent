package liao.lian.chat.client.impl;

import entity.Result;
import entity.StatusCode;
import liao.lian.chat.client.MesClient;

import java.util.Map;

public class MesClientImpl implements MesClient {
    @Override
    public Result saveMessage(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }

    @Override
    public Result getSystemMessages(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }

    @Override
    public Result setReadStatus(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }

    @Override
    public Result setMessageStatus(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }

    @Override
    public Result addFriend(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }

    @Override
    public Result addGroupUsers(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }

    @Override
    public Result loadMoreMessages(Map map) {
        return new Result(false, StatusCode.ERROR.getCode(),"熔断器启动了");
    }
}
