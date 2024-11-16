## 스프링부트 - 핵심 원리와 활용
(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%ED%95%B5%EC%8B%AC%EC%9B%90%EB%A6%AC-%ED%99%9C%EC%9A%A9)
<br />
<br />
<br />

### 스프링부트 없이
1. 서블릿 동작1 (@WebServlet)
````
    1. 노출할 html 파일 생성 (/webapp/index.html)
    2. 서블릿 추가 (hello/servlet/testservlet)
    3. 추가한 servlet war빌드 후 배포
        3.1 (경로) ./gradlew build => 프로젝트 디렉토리에 build 생성 (터미널)
        3.2 build/libs/war  (터미널)
        3.3 jar -xvf server-0.0.1-SNAPSHOT.war (압축 해제 그냥한번 해봄..) + open .
        3.4 설치한 톰캣의 webapps 경로에 war 파일 이동 -> tomcat/webapps/ROOT.war(server-0.0.1-SNAPSHOT.war -> ROOT.war 이름 변경)
        3.5 톰캣 실행 (./startup.sh)
    
````

<br />
<br />

2. 서블릿 컨테이너 초기화
````
    1. 서블릿컨테이너이니셜라이저 구현 (MyContainerInitV1 implements ServletContainerInitializer)
    1.1 onStartup 메소드 오버라이드
    2. 서블릿 컨테이너의 초기화 메소드 명시를 위한 meta-inf 생성
    3. resources/META-INF.services/jakarta.servlet.ServletContainerInitializer 추가 (대문자 소문자 규칙준수 필수)
    3.1 서블릿 컨테이너 인터페이스 구현체 경로 명시 (hello.container.MyContainerInitV1)
    4. 톰캣 실행 -> onStartup 실행 확인
````

3. 서블릿 동작2 (프로그래밍 방식)
````
    1. 서블릿 테스트용 컨트롤러 생성 (HelloServlet)
    2. 서블릿 컨텍스트 처리를 위한 인터페이스 생성 (AppInit)
    3. 구현체 생성 후 서블릿 코드 등록 (HelloServlet) 
    
    
    1번 동작 방식에 비해 하드코딩이 추가되지만 조건이나 특정 서블릿을 따로 관리할 수 있기 때문에
    훨씬 유연하게 사용 가능하다.
````
