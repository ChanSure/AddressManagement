# web
### 个人通讯录管理系统Part1
**注：本次作业三个静态页面都基于Bootstrap架构**
#### 0.自定义CSS文件(mystyle.css)
定义了两个样式表
#### 1.登录页面(login.html)
基础的Bootstrap登录模板，如图<br>
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_login.png" width="720" height="405" align="bottom">
#### 2.通讯录列表页面(addresslist.html)
增加查询功能，设计可以按照姓名、电话、QQ、邮箱进行查询<br>
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_addresslist.png" width="720" height="405" align="bottom">
#### 3.添加新的联系人页面(add.html)
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_add.png" width="720" height="405" align="bottom"><br>
其中，根据各大主流邮箱，将邮箱格式设置为“邮箱不能以 - _ .以及其它特殊字符开头和结束且域名为2到5个字母”。<br>
使用正则表达式来完成输入邮箱的校验<br>
`^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,5}$`<br>
当邮箱输入栏失去焦点时进行校验<br>
<img src="https://cs-picture.oss-cn-qingdao.aliyuncs.com/web/view_adderr.png" width="720" height="405" align="bottom"><br>