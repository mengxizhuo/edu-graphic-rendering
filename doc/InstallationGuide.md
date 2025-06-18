# 图形渲染系统安装指南

## 系统要求

在安装图形渲染系统之前，请确保您的系统满足以下要求：

- **操作系统**：Windows 10/11、macOS 10.15+、Linux (Ubuntu 20.04+)
- **Java**：Java Development Kit (JDK) 11 或更高版本
- **Maven**：Apache Maven 3.6 或更高版本

## 安装步骤

### 1. 安装 Java JDK

#### Windows
1. 访问 [Oracle JDK 下载页面](https://www.oracle.com/java/technologies/downloads/) 或 [OpenJDK 下载页面](https://adoptium.net/)
2. 下载适用于 Windows 的 JDK 11 或更高版本
3. 运行安装程序并按照向导完成安装
4. 设置 JAVA_HOME 环境变量：
   - 右键点击"此电脑"，选择"属性"
   - 点击"高级系统设置"
   - 点击"环境变量"
   - 在系统变量中添加 JAVA_HOME，值为 JDK 安装路径（例如：C:\Program Files\Java\jdk-11）
   - 在 Path 变量中添加 %JAVA_HOME%\bin
5. 验证安装：打开命令提示符，输入 `java -version`

#### macOS
1. 使用 Homebrew 安装 OpenJDK：
   ```
   brew install openjdk@11
   ```
2. 设置 JAVA_HOME 环境变量：
   ```
   echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 11)' >> ~/.zshrc
   source ~/.zshrc
   ```
3. 验证安装：打开终端，输入 `java -version`

#### Linux (Ubuntu)
1. 使用 apt 安装 OpenJDK：
   ```
   sudo apt update
   sudo apt install openjdk-11-jdk
   ```
2. 设置 JAVA_HOME 环境变量：
   ```
   echo 'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64' >> ~/.bashrc
   source ~/.bashrc
   ```
3. 验证安装：打开终端，输入 `java -version`

### 2. 安装 Maven

#### Windows
1. 访问 [Apache Maven 下载页面](https://maven.apache.org/download.cgi)
2. 下载最新的二进制 zip 归档文件
3. 解压到您选择的目录（例如：C:\Program Files\Apache\maven）
4. 设置环境变量：
   - 在系统变量中添加 MAVEN_HOME，值为 Maven 安装路径
   - 在 Path 变量中添加 %MAVEN_HOME%\bin
5. 验证安装：打开命令提示符，输入 `mvn -version`

#### macOS
1. 使用 Homebrew 安装 Maven：
   ```
   brew install maven
   ```
2. 验证安装：打开终端，输入 `mvn -version`

#### Linux (Ubuntu)
1. 使用 apt 安装 Maven：
   ```
   sudo apt update
   sudo apt install maven
   ```
2. 验证安装：打开终端，输入 `mvn -version`

### 3. 获取项目代码

1. 克隆项目仓库或下载项目代码压缩包
2. 解压项目代码到您选择的目录

### 4. 构建项目

1. 打开命令行终端
2. 导航到项目根目录（包含 pom.xml 文件的目录）
3. 运行以下命令构建项目：
   ```
   mvn clean install
   ```
4. 构建成功后，运行以下命令打包项目：
   ```
   mvn package
   ```

### 5. 运行项目

构建成功后，您可以使用以下命令运行项目：

```
java -jar target/graphic-rendering-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## 故障排除

### 常见问题

1. **问题**：`java: 无法找到符号` 或 类似编译错误
   **解决方案**：确保您使用的是 JDK 11 或更高版本，并且 JAVA_HOME 环境变量设置正确

2. **问题**：`mvn 不是内部或外部命令` 或类似错误
   **解决方案**：确保 Maven 安装正确，并且已添加到 PATH 环境变量中

3. **问题**：依赖项下载失败
   **解决方案**：检查网络连接，可能需要配置 Maven 代理或镜像

4. **问题**：运行时出现 `ClassNotFoundException`
   **解决方案**：确保使用了正确的 jar 文件（带有依赖项的 jar）

## 联系支持

如果您在安装过程中遇到任何问题，请联系mengxizhuo@gmail.com获取支持。 