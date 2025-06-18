@echo off
echo 正在启动图形渲染系统...

REM 检查Java版本
java -version 2>&1 | findstr "version" > nul
if %errorlevel% neq 0 (
    echo 未检测到Java，请安装Java 11或更高版本
    pause
    exit /b 1
)

REM 检查是否已经编译
if not exist "target\graphic-rendering-1.0-SNAPSHOT-jar-with-dependencies.jar" (
    echo 正在编译项目...
    call mvn clean package
    if %errorlevel% neq 0 (
        echo 编译失败，请检查错误信息
        pause
        exit /b 1
    )
)

REM 运行程序
echo 启动应用程序...
java -jar target\graphic-rendering-1.0-SNAPSHOT-jar-with-dependencies.jar

pause 