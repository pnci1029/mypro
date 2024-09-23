`요구사항`
````
    1. 선착순 100명에게만 쿠폰이 지급되어야한다.
    
    2. 101개 이상이 지급되면 안된다.
    
    3. 순간적으로 몰리는 트래픽을 버틸 수 있어야한다.
````

<br />
<br />

`멀티스레드 문제를 개선하기 위한 방법 1. redis`
````
    export COMPOSE_PROJECT_NAME=coupon

    싱글 스레드 환경의 레디스는 
    incr 명령어를 통해서 원하는 id에 대한 카운트를 관리할 수 있다.
    
    
    docker pull redis
    docker run --name myredis -d -p 6379:6379 redis
    docker exec -it b8d0(컨테이너 이름) redis-cli
    
    flushall
````

<br />

`문제점`
````
    1. RDB에 지속적인 부하 발생 (요청이 큰 경우)
    
    2. 동시에 많은 양을 처리하는 도중 다른 인서트 동작이 발생할 경우 타임아웃 문제 발생 가능
        -> ex) insrt를 1분에 10000건 가능한 상황에 100,000건의 인서트 요청이 들어왔을 경우,
               그 사이 회원가입이나 주문 insert가 요청되었을때 100,000건의 요청이 전체가 처리되지 않을 수 있다.
````

<br />
<br />

`2. kafka`
````

    docker-compose.yml
    
    version: '2'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    container_name: zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka:2.12-2.5.0
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
````

<br />

````
    1. 토픽 생성
        docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic testTopic
        
    2. 프로듀서 실행
        docker exec -it kafka kafka-console-producer.sh --topic testTopic --broker-list 0.0.0.0:9092
        
    3. 컨슈머 실행
        docker exec -it kafka kafka-console-consumer.sh --topic testTopic --bootstrap-server localhost:9092
        
    - 토픽 생성
        docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic coupon_create
        
    - 컨슈머 실행(로깅)
        docker exec -it kafka kafka-console-consumer.sh --topic coupon_create --bootstrap-server localhost:9092 --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"2
````