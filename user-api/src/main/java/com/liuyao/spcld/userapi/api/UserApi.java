package com.liuyao.spcld.userapi.api;

import com.liuyao.spcld.userapi.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/user")
public interface UserApi {

    /**
     * 可以写注释
     * @return
     */
    @GetMapping("/alive")
    public String alive();

    /**
     * 如果此接口的实现是feign
     *  则此处参数的注解是给feign用的
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public String getById(@RequestParam("id") String id);

    @GetMapping("/getMap")
    public Map<String, Object> getMap(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    public Map<String, Object> postMap(@RequestBody Map<String, Object> map);

    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person);
}
