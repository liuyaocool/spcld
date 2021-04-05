package com.liuyao.spcld.loginjwt.system;

import com.liuyao.spcld.loginjwt.user.Role;
import com.liuyao.spcld.loginjwt.user.User;
import com.liuyao.spcld.loginjwt.user.UserService;
import com.liuyao.spcld.loginjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("${auth.not.path}")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("") // 此处若为 "/"，则url必须以 / 结尾
    public String list(HttpServletRequest req) {
        return "login";
    }

    @GetMapping("/error")
    public String error(HttpServletRequest req) {
        return "error";
    }

    @GetMapping("/powerover")
    public String powerover(HttpServletRequest req) {
        return "powerover";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResBody get(HttpServletRequest req, HttpServletResponse resp, @RequestBody User user) {
        if (null == user.getPassword() || "".equals(user.getPassword())
                || null == user.getUsername() || "".equals(user.getUsername())){
            return new ResBody(403, "用户名或密码为空。");
        }
        User queryUser = userService.getUser(user);
        if (null == queryUser || !user.getPassword().equals(queryUser.getPassword())) {
            return new ResBody(403, "用户名或密码错误。");
        }
        String token = queryUser.getUsername() + ";";
        Iterator<String> roles = queryUser.getRoles().iterator();
        while (roles.hasNext()) {
            token += roles.next() + ",";
        }
        token = token.substring(0, token.length()-1) + ";";
        token = JwtUtil.createToken(token);

        setTokenToCookie(resp, Integer.MAX_VALUE, req.getContextPath(), token);
        return new ResBody(200, "success");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getContextPath();

        setTokenToCookie(resp, 0, path, null);

        try {
            resp.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 添加token到cookie
    private void setTokenToCookie(HttpServletResponse resp, int age, String path, String token) {
        Cookie ck = new Cookie("token", token);
        ck.setMaxAge(age);
        ck.setPath(null == path || "".equals(path.trim()) ? "/" : path);
        resp.addCookie(ck);

    }
}
