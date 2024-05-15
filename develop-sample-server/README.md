# 本地代理
```
set http_proxy=http://127.0.0.1:7897

set https_proxy=http://127.0.0.1:7897
```


# naocs
```agsl
docker run --name nacos-quick -e MODE=standalone -p 8849:8848 -p 9849:9848 -d nacos/nacos-server:2.0.2
```

# mysql
```agsl
docker run -d --name mysql8 -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 --privileged=true mysql:8.0 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

# Redis
```agsl
docker run --name redis -d  -p 6369:6379 --privileged=true redis
```
# rabbitMq
```agsl

```
