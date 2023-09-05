# 자바 버전 8 이미지로부터 시작합니다.
FROM openjdk:11

# LABEL을 설정합니다
LABEL MAINTAINER="myMaintainer"

# 환경변수를 설정합니다
ENV PROJECT_PATH="/app/target"

# 쉘 커맨드로 volume 폴더를 생성합니다.
RUN mkdir volume

# 해당 이미지의 리눅스 패키지 업데이트, 설치도 가능합니다. (ex) RUN apt-get install -y net-tools
RUN apt-get update

# 현재 도커파일이 위치한 폴더의 모든 데이터를 도커 컨테이너 내부 /app 폴더로 복사합니다.
ADD ./ /app

# 디렉토리 위치를 /app으로 옮깁니다.
WORKDIR /app

# 패키징을 합니다
RUN ./gradlew build

# 위에서 설정했던 환경변수에 등록된 내용을 사용하여 디렉토리 위치를 옮깁니다
WORKDIR $PROJECT_PATH

# 노출 시킬 포트 번호를 정합니다
EXPOSE 8081

# 호스트 에서 마운트 시킬 볼륨을 설정합니다
VOLUME [ "/volume" ]

# 시작 될 때 실행할 쉘 커맨드를 설정합니다.
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]