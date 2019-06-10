package com.baizhi.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

   /* @Autowired(required = false)
    private AdminService adminService;*/

    //登陆
    /* @RequestMapping("/login")*/
    /*public @ResponseBody
    HashMap<String,String> login(Admin admin, HttpSession session,String code){
        HashMap<String, String> map = new HashMap<>();
        //从session中获取验证码
        String securityCode = (String) session.getAttribute("securityCode");
        Admin username = adminService.selectByUsername(admin);
        //判断验证码是正确
        if(code.equals(securityCode)){
            //判断姓名是否为空
            if(username!=null){
                Admin login = adminService.login(admin);
                System.out.println("控制层~~~"+login);
                if(login!=null){
                    session.setAttribute("login",login);
                    map.put("message","yes");
                }else{
                    map.put("message","no");
                }
            }
        }else{
            map.put("message","yan");
        }
        return map;
    }
}
*/


   /* public String login(String username,String password,String Code){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return "ok";
    }*/
}