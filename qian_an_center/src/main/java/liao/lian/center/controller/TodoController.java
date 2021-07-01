package liao.lian.center.controller;

import entity.Result;
import liao.lian.center.pojo.Todo;
import liao.lian.center.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService todoService;


    /**
     * 获取日程安排
     * @param map
     * @return
     */
    @PostMapping("getTodoList")
    public Result getTodoList(@RequestBody Map map){
        return todoService.getTodoList(map);
    }

    /**
     * 添加日程安排
     * @param todo
     * @return
     */
    @PostMapping("addTodo")
    public Result addTodo(@RequestBody Todo todo){
        return todoService.addTodo(todo);
    }

    /**
     * 更新日程安排
     * @param todo
     * @return
     */
    @PostMapping("upTodo")
    public Result upTodo(@RequestBody Todo todo){
        return todoService.upTodo(todo);
    }

    /**
     * 删除日程安排
     * @param map
     * @return
     */
    @PostMapping("delTodo")
    public Result delTodo(@RequestBody Map map){
        return todoService.delTodo(map);
    }
}
