# DataMonitoring
一个嵌入式开发基于tcp协议serversocket端，用于显示数据

注意:请先下载本说明指导(README.md)方便阅读


1.环境配置说明:
  请确保在您的机器上已经安装Java JDK运行环境 这里使用IDEA作为例子工具说明配置环境步骤。
  
2.具体环境配置步骤:
  (1)下载该版本源码,直接使用git clone 或者 download 到您的机器上
  
  (2)使用IDEA打开它 会自动下载pom.xml的内容(该过程在内地网络下载速度会变慢，可以百度Maven换源 也可以参考我下面的步骤(3) )
  
  (3)如果再下载依赖(pom.xml)的过程中速度慢，请先排除是否是您的网络本身问题，如果不是请修改，基于阿里云的Maven镜像源
     在IDEA中找到File->Settings->Build,Execution,Deployment->Build Tools->Maven右侧的User Settings file:
     在Override上打勾 然后将内容框里面指定的文件进行重新编辑，将以下内容覆盖其中:
     
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <mirrors>
    <mirror>
    <id>aliyun-public</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun public</name>
    <url>https://maven.aliyun.com/repository/public</url>
</mirror>

<mirror>
    <id>aliyun-central</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun central</name>
    <url>https://maven.aliyun.com/repository/central</url>
</mirror>

<mirror>
    <id>aliyun-spring</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun spring</name>
    <url>https://maven.aliyun.com/repository/spring</url>
</mirror>

<mirror>
    <id>aliyun-spring-plugin</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun spring-plugin</name>
    <url>https://maven.aliyun.com/repository/spring-plugin</url>
</mirror>

<mirror>
    <id>aliyun-apache-snapshots</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun apache-snapshots</name>
    <url>https://maven.aliyun.com/repository/apache-snapshots</url>
</mirror>

<mirror>
    <id>aliyun-google</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun google</name>
    <url>https://maven.aliyun.com/repository/google</url>
</mirror>

<mirror>
    <id>aliyun-gradle-plugin</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun gradle-plugin</name>
    <url>https://maven.aliyun.com/repository/gradle-plugin</url>
</mirror>

<mirror>
    <id>aliyun-jcenter</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun jcenter</name>
    <url>https://maven.aliyun.com/repository/jcenter</url>
</mirror>

<mirror>
    <id>aliyun-releases</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun releases</name>
    <url>https://maven.aliyun.com/repository/releases</url>
</mirror>

<mirror>
    <id>aliyun-snapshots</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun snapshots</name>
    <url>https://maven.aliyun.com/repository/snapshots</url>
</mirror>  

<mirror>
    <id>aliyun-grails-core</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun grails-core</name>
    <url>https://maven.aliyun.com/repository/grails-core</url>
</mirror>

<mirror>
    <id>aliyun-mapr-public</id>
    <mirrorOf>*</mirrorOf>
    <name>aliyun mapr-public</name>
    <url>https://maven.aliyun.com/repository/mapr-public</url>
</mirror>
  </mirrors>
<repository>
    <id>spring</id>
    <url>https://maven.aliyun.com/repository/spring</url>
    <releases>
        <enabled>true</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
</settings>

  (4)完成以上加载步骤pom.xml不会显示红色错误即可，然后可以构建运行该程序,在浏览器中输入localhost:8080  即可访问主页，此时回到IDEA界面的控制台      (Console) 会提示 输入待监听的端口:  这里可以自定义  我以10000端口为例输入并回车。
     这时主程序的serversocket已经在对该端口进行监听  你会看到页面并没有反应，无需刷新，因为还没有接收到来自本地localhost的socket连接，这里我        提供了一个模拟客户端监测数据的程序TCPClient.java
     
  (5)运行TCPClient.java 控制台会提示输入要建立socket的端口:  这里的端口必须与之前的10000端口一致，输入10000并回车确认，然后在该控制台输入一些      数据(输入的内容格式必须包含 湿度为xx.x%RH，温度为xx.x℃ )，如输入:湿度为48.8%RH，温度为26.7℃ 并回车，此时返回到浏览器之前的页面，可以看到已经有数据出现，可以进行重复输入模拟数据的收集显示到页面。
  (6)以上的第(5)只是对于没有客户端监测时的测试，如果有设备进行监测，则无需运行该步骤的TCPClient.java

3.以上便是本次的全部环境配置以及运行操作说明
  
  
