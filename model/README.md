# 图形渲染系统 UML 模型

本目录包含图形渲染系统的 UML 模型，使用 PlantUML 实现。

## 目录结构

```
/model/
  ├── /plantuml/                # PlantUML 源文件
  │   ├── class-diagram.puml    # 类图
  │   ├── sequence-diagram-add-shape.puml    # 添加图形序列图
  │   ├── sequence-diagram-render.puml       # 渲染图形序列图
  │   ├── sequence-diagram-visitor.puml      # 访问者模式序列图
  │   ├── sequence-diagram-command.puml      # 命令模式序列图
  │   ├── sequence-diagram-observer.puml     # 观察者模式序列图
  │   ├── sequence-diagram-proxy.puml        # 代理模式序列图
  │   ├── sequence-diagram-adapter.puml      # 适配器模式序列图
  │   ├── sequence-diagram-factory.puml      # 工厂模式序列图
  │   ├── sequence-diagram-abstract-factory.puml  # 抽象工厂模式序列图
  │   ├── sequence-diagram-singleton.puml    # 单例模式序列图
  │   ├── component-diagram.puml             # 组件图
  │   ├── use-case-diagram.puml              # 用例图
  │   ├── activity-diagram.puml              # 活动图
  │   └── design-patterns.puml               # 设计模式关系图
  └── README.md                  # 本文件
```

## 如何查看这些图

1. **在线查看**：
   - 访问 [PlantUML Online Server](http://www.plantuml.com/plantuml/uml/)
   - 复制任意 `.puml` 文件的内容并粘贴到编辑器中
   - 点击 "Submit" 按钮查看生成的图

2. **本地查看**：
   - 安装 [PlantUML](https://plantuml.com/download)
   - 使用 PlantUML 命令行工具或集成开发环境插件生成图像

## 图表说明

### 类图 (class-diagram.puml)
展示系统中所有类、接口及其关系，包括继承、实现、关联、依赖等。

### 序列图
- **添加图形序列图**：展示添加图形的交互流程
- **渲染图形序列图**：展示渲染图形的交互流程
- **访问者模式序列图**：展示访问者模式的交互流程
- **命令模式序列图**：展示命令模式的交互流程
- **观察者模式序列图**：展示观察者模式的交互流程
- **代理模式序列图**：展示代理模式的交互流程
- **适配器模式序列图**：展示适配器模式的交互流程
- **工厂模式序列图**：展示工厂模式的交互流程
- **抽象工厂模式序列图**：展示抽象工厂模式的交互流程
- **单例模式序列图**：展示单例模式的交互流程

### 组件图 (component-diagram.puml)
展示系统的主要组件及其依赖关系。

### 用例图 (use-case-diagram.puml)
展示系统的功能需求和用户交互。

### 活动图 (activity-diagram.puml)
展示系统的工作流程和业务逻辑。

### 设计模式关系图 (design-patterns.puml)
展示系统中使用的9种设计模式及其关系。 