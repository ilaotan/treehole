# treehole[树洞]

## 2017-04-25
### 完善了install页面
### 实现了
* 安装时声称install.lock标识文件
* 安装时第一步验证数据库可用性

## 2017-05-08  下一步设想
### 博客基础信息页
* 用户信息
    * 用户名 
    * 密码 
    * 邮箱
* 安装信息
    * 安装日期
    * 博客版本
    * 系统型号
* 友链信息
    * 名称
    * 链接
    * 添加日期
    
    
> mvn clean package assembly:single -Pprod -Dmaven.test.skip=true