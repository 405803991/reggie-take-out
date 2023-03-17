package xjtu.demo1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xjtu.demo1.common.R;
import xjtu.demo1.entity.User;
import xjtu.demo1.service.UserService;
import xjtu.demo1.utils.SMSUtils;
import xjtu.demo1.utils.ValidateCodeUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        // 发送验证码

        String phone = user.getPhone();

        if(StringUtils.isNotEmpty(phone)) {

            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            SMSUtils.sendMessage("123", "", phone, code);

            session.setAttribute(phone, code);

            return R.success("success");
        }
        return R.error("failed");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        //未实际实现验证码比对
        String phone = map.get("phone").toString();

        String code = map.get("code").toString();

        Object codeInSession = session.getAttribute(phone);

        if (/*codeInSession != null && codeInSession.equals(code)*/ true ) {
            LambdaQueryWrapper<User> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            objectLambdaQueryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(objectLambdaQueryWrapper);
            if(user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }

        return R.error("error");
    }

    @PostMapping("/loginout")
    public R<String> loginout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

}
