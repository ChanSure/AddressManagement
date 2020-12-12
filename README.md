# AddressManagement
- [AddressManagement Part1](#addressmanagement-part1)
  * [0 CSS mystyle.css](#0-css-mystylecss)
  * [1 login page login.html](#1-login-page-loginhtml)
  * [2 main page addresslist.html](#2-main-page-addresslisthtml)
  * [3 add page add.html](#3-add-page-addhtml)
- [AddressManagement Part2](#addressmanagement-part2)
  * [1 login](#1-login)
  * [2 main](#2-main)
  * [3 add](#3-add)
  * [4 update](#4-update)
  * [5 delete](#5-delete)
- [AddressManagement Part2](#addressmanagement-part2-1)
  * [1 AJAX](#1-ajax)
  * [2 JPA](#2-jpa)
***
### AddressManagement Part1
**注：本次作业三个静态页面都基于Bootstrap**<br>
目录：AddressManagement/src/main/resources/static/
#### 0 CSS mystyle.css
定义了两个样式表
#### 1 login page login.html
基础的Bootstrap登录模板，如图<br>
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_login.png" width="720" height="405" align="bottom">
#### 2 main page addresslist.html
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_addresslist.png" width="720" height="405" align="bottom"><br>
#### 3 add page add.html
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_add.png" width="720" height="405" align="bottom"><br>
其中，根据各大主流邮箱，将邮箱格式设置为“邮箱不能以 - _ .以及其它特殊字符开头和结束且域名为2到5个字母”。<br>
使用正则表达式来完成输入邮箱的校验<br>
`^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,5}$`<br>
当邮箱输入栏失去焦点时进行校验<br>
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_adderr.png" width="720" height="405" align="bottom"><br>

---
### AddressManagement Part2
本次作业中，所有数据都通过session模拟。实现了登录，查看，添加，删除，修改功能。<br>
同时，实现了一个**拦截器**，未登录的时候，所有访问都会被重定向到登录页面。
#### 1 login
登录页面，首先测试错误用户名：
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-1.png" width="720" height="405" align="bottom"><br>
在下方显示登录失败，并重定向回登录界面，重新输入正确用户名和密码
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-2.png" width="720" height="405" align="bottom"><br>
#### 2 main
输入正确的用户名和密码后会跳转到主界面，即通讯录列表
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-3.png" width="720" height="405" align="bottom"><br>
#### 3 add
点击添加，便会跳转到添加页面
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-4.png" width="720" height="405" align="bottom"><br>
添加成功则会返回主界面
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-5.png" width="720" height="405" align="bottom"><br>
#### 4 update
点击修改，则会跳转到修改页面，其中，用户名是无法修改的。
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-6.png" width="720" height="405" align="bottom"><br>
修改成功则会返回主界面
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-7.png" width="720" height="405" align="bottom"><br>
#### 5 delete
点击删除，则会删除相应的记录。<br>
删除功能中，使用了`XMLHttpRequest`对象，能够使得在不刷新页面的情况下更新网页。
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/part2-8.png" width="720" height="405" align="bottom"><br>

---
### AddressManagement Part2
#### 1 AJAX
首先添加电话为13156254899的成员，提示电话不存在重复。
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-1.png" width="720" height="405" align="bottom"><br>
添加成功：
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-2.png" width="720" height="405" align="bottom"><br>
再次添加相同号码的记录，显示已经存在：
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-3.png" width="720" height="405" align="bottom"><br>
在拦截器中添加判断：
```java
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object isLogin = request.getSession().getAttribute("login");
        if(request.getRequestURI().endsWith("/login")||request.getRequestURI().endsWith("/loginCheck")){
            return true;
        }
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有，x-requested-with
            System.out.print("发生ajax请求...");
            return true;
        }
        if(isLogin == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
```
控制台输出：
<br><img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-4.png" align="bottom"><br>
#### 2 JPA
登录数据库：
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-5.png" width="720" height="405" align="bottom"><br>
先查询已有记录
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-6.png" width="720" height="405" align="bottom"><br>
添加用户
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-7.png" width="720" height="405" align="bottom"><br>
再次查询数据库
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-8.png" width="720" height="405" align="bottom"><br>
删除第一条记录
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-9.png" width="720" height="405" align="bottom"><br>
再次查询
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/web5-10.png" width="720" height="405" align="bottom"><br>
