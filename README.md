# 图形渲染系统 (Graphics Rendering System)

## 项目简介
本项目是一个基于Java的图形渲染系统，综合运用了9种设计模式，包括工厂模式、抽象工厂模式、单例模式、适配器模式、桥接模式、代理模式、命令模式、访问者模式和观察者模式。

## 功能特点
- 渲染基本图形元素（圆形、矩形、线段、三角形）
- 支持多种渲染引擎（SVG、Swing、终端字符图）
- 用户操作命令系统（添加、移动、撤销、重做）
- 图形访问器（导出为JSON、XML等格式）
- 插件代理机制（远程渲染服务）

## 项目结构
```
/graphic-rendering/
  ├── /doc/           # 项目文档
  ├── /model/         # PlantUML模型文件
  ├── /src/           # 源代码
  │    ├── /main/     # 主代码
  │    └── /test/     # 测试代码
  ├── /javadoc/       # 生成的JavaDoc文档
  ├── /target/        # 编译输出
  │    └── /site/     # 生成的站点文档
  └── README.md       # 项目说明
```

## 设计模式应用
1. 创建型模式
   - Factory：图形工厂，创建具体图形对象
   - Abstract Factory：渲染引擎工厂，创建不同渲染引擎
   - Singleton：系统配置单例

2. 结构型模式
   - Adapter：渲染器适配器，适配不同图形库
   - Bridge：渲染器接口与实现，支持多种渲染方式
   - Proxy：渲染服务代理，实现本地与远程渲染

3. 行为型模式
   - Command：图形命令操作，实现操作和撤销
   - Visitor：数据导出，导出为不同格式
   - Observer：监听模型变化

## 开发环境要求

- Java 11或更高版本
- Maven 3.6或更高版本
- Doxygen（用于生成Doxygen文档）

## 构建与运行

### 构建项目

使用Maven构建项目：

```bash
mvn clean package
```

### 运行应用

运行应用程序：

```bash
java -jar target/graphic-rendering-1.0-SNAPSHOT-jar-with-dependencies.jar
```

或在Windows上使用批处理文件：

```bash
run.bat
```

## 测试

### 运行测试

运行单元测试和集成测试：

```bash
mvn test
```

### 生成测试报告和代码覆盖率

生成测试报告和代码覆盖率：

```bash
mvn verify
```

测试报告将位于：
- `target/site/surefire-report.html`
- `target/site/jacoco/index.html`

## 文档

### 生成JavaDoc

生成JavaDoc文档：

```bash
mvn javadoc:javadoc
```

JavaDoc将位于`javadoc/index.html`。

### 生成Doxygen文档

生成Doxygen文档：

```bash
mvn doxygen:report
```

Doxygen文档将位于`target/site/doxygen/html/index.html`。

### 生成完整站点文档

生成完整站点文档，包括JavaDoc、Doxygen、测试报告和代码覆盖率：

```bash
mvn site
```

站点将位于`target/site/index.html`。

站点包括：
- 项目概述和摘要
- JavaDoc API文档
- Doxygen文档
- 项目报告
- 依赖关系
- 测试结果
- 代码覆盖率

## 开发团队
软件工程课程期末大作业