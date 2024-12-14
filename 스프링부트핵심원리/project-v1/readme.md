## 스프링부트 - 핵심 원리와 활용
(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%ED%95%B5%EC%8B%AC%EC%9B%90%EB%A6%AC-%ED%99%9C%EC%9A%A9)
<br />
<br />
<br />

### 라이브러리 등록 및 사용
````
    1. 외부라이브러리 생성
    2. 생성한 외부 라이브러리 jar파일 추출
        2.1 프로젝트 최상위 - ./gradlew clean build
        2.2 /build/libs 내부 jar 파일 확인
    3. 라이브러리 받으려는 프로젝트 최상위 /libs 디렉토리에 jar파일 추가
    4. build.gradle - dependencies에 추가
        ex) implementation files('libs/memory-v1.jar')
````