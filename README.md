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
To be continued...