## 스프링부트 - 핵심 원리와 활용
(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%ED%95%B5%EC%8B%AC%EC%9B%90%EB%A6%AC-%ED%99%9C%EC%9A%A9)
<br />
<br />
<br />

### 스프링부트 없이
1. @Conditional
````
    @ConditionalOnClass,
    -> 클래스가 있는 경우 동작. 나머지는 X
    
    @ConditionalOnBean
    -> 빈이 등록되어 있는 경우 동작. 나머지는 X
    
    @ConditionalOnProperty
    -> 환경정보가 있는 경우 동작. 나머지는 X
    
    @ConditionalOnResource
    -> 리소스가 있는 경우 동작. 나머지는 X
    
    @ConditionalOnWebApplication
    -> 웹 애플리케이션인 경우 동작. 나머지는 X
    
    @ConditionalOnExpression
    -> SqEL 표현식에 만족하는 경우 동작. 나머지는 X
    
````

<br />
<br />