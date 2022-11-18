#!/bin/bash

PID=`ps -ef|grep -E "biz-system-service|biz-system-web|biz-business-service|biz-business-web|base-register-center|base-spring-gateway"  | awk '{print $2}'`
echo "得到进程id:$PID"

echo "结束进程"
for id in $PID
do
	kill -9 $id
	echo "kill $id"
done
echo "结束进程完成"

echo "重启biz-system-service服务"
nohup java -jar /opt/www/service/biz-system-service.jar --spring.profiles.active=online > biz-system-service.log 2>&1 &
sleep 1

echo "重启biz-system-web服务"
nohup java -jar /opt/www/service/biz-system-web.jar --spring.profiles.active=online > biz-system-web.log 2>&1 &
sleep 1

echo "重启biz-business-service服务"
nohup java -jar /opt/www/service/biz-business-service.jar --spring.profiles.active=online > biz-business-service.log 2>&1 &
sleep 1

echo "重启biz-business-web服务"
nohup java -jar /opt/www/service/biz-business-web.jar --spring.profiles.active=online > biz-business-web.log 2>&1 &
sleep 1

echo "重启base-register-center服务"
nohup java -jar /opt/www/service/base-register-center.jar --spring.profiles.active=online > base-register-center.log 2>&1 &
sleep 1

echo "重启base-spring-gateway服务"
nohup java -jar /opt/www/service/base-spring-gateway.jar --spring.profiles.active=online > base-spring-gateway.log 2>&1 &
sleep 1

echo "执行完成"
echo "查看进程信息"
ps -ef|grep -E "biz-system-service|biz-system-web|biz-business-service|biz-business-web|base-register-center|base-spring-gateway"