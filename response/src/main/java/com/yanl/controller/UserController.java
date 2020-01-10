package com.yanl.controller;

import com.yanl.domain.User;
import com.yanl.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: YanL
 * @Time: 13:43 2020/1/6
 * @Email: imyanl.dt@gmail.com
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 请求参数的绑定
     * String类型返回值
     * @return success
     */
    @RequestMapping("/testString")
    public String testString(Model model){
        //模拟从数据库中查询出user对象
        User user = new User();
        user.setUsername("刘炎");
        user.setPassword("5211");
        user.setMoney(1000f);
        model.addAttribute("user", user);
        return "success";
    }


    /**
     * 控制器返回值为void
     * 默认会跳转到testVoid页面，会报404异常
     * 可以使用请求转发和重定向跳转到指定页面
     * 使用Servlet的 api
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("请求转发和重定向");
        //请求转发
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);


        /*
        重定向
        无法使用WEB-INF文件夹里的文件
         */
        //response.sendRedirect(request.getContextPath() + "/redirect.jsp");

        //直接进行响应
        //解决中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("你好");
        return;

    }

    /**
     * 返回值为ModelAndVIew
     * 可以传入视图的名称（跳转的页面），还可以传入对象
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv = new ModelAndView();
        //跳转到success页面
        mv.setViewName("success");

        //模拟从数据库查询用户信息 返回到request对象
        User user = new User();
        user.setUsername("子尧");
        user.setPassword("211");
        user.setMoney(2000f);
        mv.addObject("user", user);

        return mv;
    }

    /**
     * 使用关键字转发和重定向
     * forward不走试图解析器，要写完整路径
     * @return
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        //请求转发  forward 关键字
//        return "forward:/WEB-INF/pages/success.jsp";
        //重定向
        return "redirect:/redirect.jsp";
    }

    /**
     * 获取请求体的数据
     * 响应ajax请求
     * @param body
     */
    @RequestMapping("/testRequestBody")
    public void testRequestBody(@RequestBody String body){
        System.out.println(body);
    }

    /**
     * 对json数据进行解析
     * json封装到bean对象
     * 后台自动封装
     * 需要jackson第三方包
     * @param user
     */
    @RequestMapping("/testJson2Bean")
    public @ResponseBody User testJson2Bean(@RequestBody User user) {
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setUsername("子尧");
        //响应
        return user;
    }

    @RequestMapping("/textException")
    public String testException() throws SysException{

        try {
            //模拟异常
            int i = 10 / 0;
        }catch (Exception e){
            e.printStackTrace();
            throw new SysException("查询所有账户出错....");
        }
        return "success";
    }

}
