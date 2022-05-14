# login_demo

#### 介绍
入职任务: 登录demo

#### 安装教程

1.  JDK11
2.  apache-maven-3.6.3

#### 接口说明

所有除了登录和注册接口需要请求头携带为 Authorization:token 的请求
登录成功后在登录接口成功后，响应头中会返回Authorization:token

1.  注册接口：POST ip:8888/regist/user

```json
{
    "userAccount":"你的用户名称",
    "userUsername":"登录账号",
    "userPassword":"登录密码",
    "userEmail":"你的邮箱",
    "userPhone":"你的手机号",
    "userGender":"女0，男1",
    "userAddr":"你的地址",
    "isLock":"0表示可用",
    "isAdmin":"0表示不是超级管理员，1是超级管理员"
}
```

2.  登录接口
 - 用户名密码登录接口：POST localhost:8888/login

```json
{
    "username":"登录账号",
    "password":"登录密码"
}
```
    
 - 邮箱登录

     - 获取验证码接口：POST ip:8888/login/email/getCode

```json
{
    "email":"你的邮箱",
    "code":""
}
```

 - 验证码登录接口：POST ip:8888/login/email

```json
{
    "email":"你的邮箱",
    "code":"验证码"
}
```

3.  查询用户接口（请求头需要携带Authorization:token）
 - 查询所有 user 接口：GET ip:8888/user/getUsers
 - 查询 userAndArticle 接口：localhost:8888/user/getUserAndArticle
 
4.  雷达接口
 - 雷达信息查询接口：POST localhost:8888/radar
 
```json
{
    "lon":"102.71",	// 99.1 - 104.46
    "lat":"38.11",	// 34.45 - 38.74
    "level":"06",	// 1-6
    "time":"2021-01-09T11:40:00" 
}
```
5.  站点接口
 - 按时间和站点id查询接口：GET localhost:8888/stationInfo/byTimeAndStationId?stationTime=2022-01-15 06:49:00&stationIdD=50745
 - 按时间和经纬度查询：GET localhost:8888/stationInfo/byTimeAndStationRange?stationTime=2022-01-13 16:02:00&stationLon=2&endStationLon=5&stationLat=2&endStationLat=5
 - 按时间和区域查询: GET localhost:8888/stationInfo/byTimeAndStationArea?stationTime=2022-01-15 06:49:00&stationProvince=浙江省

#### interceptor说明
1.  TokenInterceptor 判断当前请求是否携带token以及合法
2.  LoggingInterceptor 收集当前请求执行日志

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request