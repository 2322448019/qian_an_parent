package liao.lian.center.service;

import entity.Result;
import entity.StatusCode;
import liao.lian.center.dao.TodoDao;
import liao.lian.center.pojo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    @Autowired
    private TodoDao todoDao;

    public Result getTodoList(Map map) {
        List<Todo> todoList = todoDao.findAllByAccount((String) map.get("account"));
        return new Result(true, StatusCode.OK.getCode(),StatusCode.OK.getMsg(),todoList);
    }

    public Result addTodo(Todo todo) {
        todoDao.save(todo);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    public Result upTodo(Todo todo) {
        todoDao.save(todo);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    public Result delTodo(Map map) {
        todoDao.deleteById((Integer) map.get("id"));
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }
}
