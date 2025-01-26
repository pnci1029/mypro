### jooq 실습을 위한 sakila db 스키마 이용

#### (https://github.com/jooq/sakila)

### 명령어

````

  1. 도커 볼륨 설정
  도커 데스크탑 설치가 완료된 상태에서

    docker volume create sakila_volume
    
  2. docker-compose.yml 작성
  version: '3.8'
  services:
    sakila-mysql:
      image: mysql:8.0.35
      
      # 애플 실리콘 맥을 사용하는 경우 아래 platform 을 주석처리 해제
      # platform: linux/amd64
      volumes:
        - sakila_volume:/data
      ports:
        - '3306:3306'
      environment:
        MYSQL_ROOT_PASSWORD: passwd
        MYSQL_DATABASE: sakila
      command:
        [ 'mysqld', '--character-set-server=utf8mb4', '--collation-server=utf8mb4_unicode_ci', '--lower_case_table_names=1' ]
  
  volumes:
    sakila_volume:
    
    
  3. docker-compose 실행
    docker-compose.yml 파일이 있는 곳에서 아래의 명령어 실행하여 컨테이너 실행
  
    docker-compose up -d
    
    
  4. 접속 확인
      localhost:3306 
      ID: root
      PW: passwd
      
      
  5. 컨테이너 종료
    docker-compose down
````

The Sakila example database
===========================

The Sakila database is a nicely normalised database modelling a DVD rental store (for those of you old enough to
remember what that is). Its design includes a few nice features:

- Many to many relationships
- Multiple paths between entities (e.g. film-inventory-rental-payment vs film-inventory-store-customer-payment) to
  practice joins
- Consistent naming of columns
    - Primary keys are called `[tablename]_[id]`
    - Foreign keys are called like their referenced primary key, if possible. This allows for using `JOIN .. USING`
      syntax where supported
    - Relationship tables do not have any surrogate keys but use composite primary keys
    - Every table has a `last_update` audit column
    - A generated data set of a reasonable size is available

ERD
===

[![ERD](https://www.jooq.org/img/sakila.png)](https://www.jooq.org/sakila)

With this database, we can try out some nice SQL queries, e.g. by using PostgreSQL syntax:

**Actor with most films (ignoring ties)**

```sql
SELECT first_name, last_name, count(*) films
FROM actor AS a
         JOIN film_actor AS fa USING (actor_id)
GROUP BY actor_id, first_name, last_name
ORDER BY films DESC
LIMIT 1;
```

Yields:

```
first_name    last_name    films
--------------------------------
GINA          DEGENERES       42
```

**Cumulative revenue of all stores**

```sql
SELECT payment_date, amount, sum(amount) OVER (ORDER BY payment_date)
FROM (SELECT CAST(payment_date AS DATE) AS payment_date, SUM(amount) AS amount
      FROM payment
      GROUP BY CAST(payment_date AS DATE)) p
ORDER BY payment_date;
```

Yields:

```
payment_date       amount         sum
-------------------------------------
2005-05-24          29.92       29.92
2005-05-25         573.63      603.55
2005-05-26         754.26     1357.81
2005-05-27         685.33     2043.14
2005-05-28         804.04     2847.18
2005-05-29         648.46     3495.64
2005-05-30         628.42     4124.06
2005-05-31         700.37     4824.43
2005-06-14          57.84     4882.27
2005-06-15        1376.52     6258.79
2005-06-16        1349.76     7608.55
2005-06-17        1332.75     8941.30
...
```

History
=======

The Sakila example database was originally developed by Mike Hillyer of the MySQL AB documentation team. it was ported
to other databases by DB Software Laboratory

License: BSD
Copyright DB Software Laboratory
http://www.etl-tools.com