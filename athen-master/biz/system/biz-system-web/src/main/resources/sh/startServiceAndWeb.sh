#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改
SERVICE_PATH=/opt/www/service
WEB_PATH=/opt/www/service

SERVICE_APP_NAME=biz-system-service
WEB_APP_NAME=biz-system-web


#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 脚本名.sh [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_service_exist(){
  pid=`ps -ef|grep $SERVICE_APP_NAME|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

is_web_exist(){
  pid=`ps -ef|grep $WEB_APP_NAME|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
service_start(){
  is_service_exist
  if [ $? -eq "0" ]; then
    echo "${SERVICE_APP_NAME} is already running. pid=${pid} ."
  else
    nohup java -jar $SERVICE_PATH/$SERVICE_APP_NAME.jar > $SERVICE_APP_NAME.log 2>&1 &
    echo "${SERVICE_APP_NAME} start success"
  fi
}

web_start(){
  is_web_exist
  if [ $? -eq "0" ]; then
    echo "${WEB_APP_NAME} is already running. pid=${pid} ."
  else
    nohup java -jar $WEB_PATH/$WEB_APP_NAME.jar > $WEB_APP_NAME.log 2>&1 &
    echo "${WEB_APP_NAME} start success"
  fi
}

#停止方法
service_stop(){
  is_service_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
  else
    echo "${SERVICE_APP_NAME} is not running"
  fi
}

web_stop(){
  is_web_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
  else
    echo "${WEB_APP_NAME} is not running"
  fi
}

#输出运行状态
service_status(){
  is_service_exist
  if [ $? -eq "0" ]; then
    echo "${SERVICE_APP_NAME} is running. Pid is ${pid}"
  else
    echo "${SERVICE_APP_NAME} is NOT running."
  fi
}

web_status(){
  is_web_exist
  if [ $? -eq "0" ]; then
    echo "${WEB_APP_NAME} is running. Pid is ${pid}"
  else
    echo "${WEB_APP_NAME} is NOT running."
  fi
}

stop(){
  service_stop
  web_stop
}

start(){
  service_start
  web_start
}
#重启
status(){
   service_status
   web_status
}
restart(){
  stop
  start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
