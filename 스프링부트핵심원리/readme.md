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

<br />
<br />

3. 서블릿 동작2 (프로그래밍 방식)
````
    1. 서블릿 테스트용 컨트롤러 생성 (HelloServlet)
    2. 서블릿 컨텍스트 처리를 위한 인터페이스 생성 (AppInit)
    3. 구현체 생성 후 서블릿 코드 등록 (HelloServlet) 
    
    
    1번 동작 방식에 비해 하드코딩이 추가되지만 조건이나 특정 서블릿을 따로 관리할 수 있기 때문에
    훨씬 유연하게 사용 가능하다.
````

<br />
<br />

4. 애플리케이션 초기화
````
    1. jakarta 서블릿 이니셜라이저에 새로운 이니셜라이저 추가(MyContainerInitV2)
    2. 초기화할 애플리케이션 인터페이스를 @HandlesTypes 어노테이션 내 추가 (@HandlesTypes(AppInit.class))
    3. 이니셜라이저에서 onStartup 실행 후 위 인터페이스의 구현체를 불러와, 서블릿 실행 
      (helloServlet.addMapping("/hello-servlet");)
````

4. 스프링 컨테이너 생성
````
    1. Rest API 통신을 위한 HelloController 생성
    2. HelloController를 빈에 등록하기 위한 컨피그 파일(HelloConfig) 생성
    3. 서블릿 구현체 생성 (AppInitV2Spring)
        3.1 스프링 컨테이너 생성
        3.2 디스패처 서블릿 생성 후 스프링 컨테이너와 연결
        3.3 생성한 디스패처 서블릿을 서플릿 컨테이너에 등록
        3.4 서블릿 컨테이너에 컨트롤러 url 매핑
        
        
    실행과정
    http://localhost:8080/spring/hello 요청
    -> 디스패처 서블릿(MyContainerInitV2 - onStartUp - "dispatcherV2") 실행 
    -> 실행된 디스패처 서블릿에서 컨트롤러 찾아서 호출
````
