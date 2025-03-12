# 校园社团管理系统

## 项目介绍
本项目是一个基于Spring Boot的校园社团管理系统，旨在为高校社团提供全面的管理解决方案。系统采用现代化的技术栈，提供直观的用户界面和丰富的功能，帮助学校和学生更好地管理社团活动。

前端项目链接：https://gitee.com/Akaisuici/campus-vue

## 技术栈
- **后端框架：** Spring Boot 2.5.9
- **数据库：** MySQL
- **ORM框架：** MyBatis-Plus 3.5.1
- **接口文档：** Swagger 3.0.0
- **安全认证：** JWT (Java-JWT 3.10.3)
- **缓存：** Redis
- **工具库：** 
  - Hutool 5.7.20
  - Apache POI 4.1.2（Excel处理）
- **开发工具：** 
  - Lombok
  - MyBatis-Plus Generator（代码生成）

## 主要功能
- 用户管理（学生、管理员）
- 社团信息管理
- 活动管理
- 成员管理
- 公告管理
- 数据统计与分析
- 文件上传下载

## 环境要求
- JDK 1.8+
- MySQL 5.7+
- Redis
- Maven 3.6+

## 快速开始
1. **克隆项目**
   ```bash
   git clone [项目地址]
   ```

2. **配置数据库**
   - 创建MySQL数据库
   - 修改 `application.properties` 中的数据库配置

3. **配置Redis**
   - 确保Redis服务已启动
   - 修改 `application.properties` 中的Redis配置

4. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

5. **访问接口文档**
   ```
   http://localhost:8080/swagger-ui/
   ```

## 项目结构
```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       ├── config/        // 配置类
│   │       ├── controller/    // 控制器
│   │       ├── entity/        // 实体类
│   │       ├── mapper/        // 数据访问层
│   │       ├── service/       // 服务层
│   │       └── utils/         // 工具类
│   └── resources/
│       ├── mapper/           // MyBatis映射文件
│       ├── application.yml   // 应用配置文件
│       └── application-dev.yml // 开发环境配置
```

## 特性
- 基于JWT的身份认证
- Redis缓存支持
- 完整的接口文档（Swagger）
- Excel导入导出
- 文件上传下载
- 统一异常处理
- 跨域支持

