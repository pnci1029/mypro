## 스프링부트 - 핵심 원리와 활용
(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%ED%95%B5%EC%8B%AC%EC%9B%90%EB%A6%AC-%ED%99%9C%EC%9A%A9)
<br />
<br />
<br />

1. 스프링부트 없이 서블릿 동작
````
    1. 노출할 html 파일 생성 (/webapp/index.html)
    2. 서블릿 추가 (hello/servlet/testservlet)
    3. 추가한 servlet war빌드 후 배포
        3.1 (경로) ./gradlew build => 프로젝트 디렉토리에 build 생성 (터미널)
        3.2 build/libs/war  (터미널)
        3.3 jar -xvf server-0.0.1-SNAPSHOT.war (압축 해제 그냥한번 해봄..) + open .
        3.4 설치한 톰캣의 webapps 경로에 war 파일 이동 -> tomcat/webapps/ROOT.war(server-0.0.1-SNAPSHOT.war -> ROOT.war 이름 변경)
    
````
