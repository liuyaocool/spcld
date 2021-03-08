package com.liuyao.spcld.userapi.api;

import com.liuyao.spcld.userapi.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 次注解与fegin+hystrix冲突
 *  若必须加 则在方法上再加一层就好了
 */
//@RequestMapping("/user")
public interface UserApi {
    static String service = "/user";

    /**
     * 可以写注释
     * @return
     */
    @GetMapping(service + "/alive")
    public String alive();

    @GetMapping(service + "/aliveTimeout")
    public String aliveTimeout();

    @GetMapping(service + "/aliveError")
    public String aliveError();

    /**
     * 如果此接口的实现是feign
     *  则此处参数的注解是给feign用的
     * @param id
     * @return
     */
    @GetMapping(service + "/getById")
    public String getById(@RequestParam("id") String id);

    @GetMapping(service + "/getMap")
    public Map<String, Object> getMap(@RequestParam Map<String, Object> map);

    @PostMapping(service + "/postMap")
    public Map<String, Object> postMap(@RequestBody Map<String, Object> map);

    @PostMapping(service + "/postPerson")
    public Person postPerson(@RequestBody Person person);
}
