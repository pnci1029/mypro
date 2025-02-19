# Readable Code
````
 중첩분기분, 중첩반복문을 줄이기 위한 사고 전환
 
 
 ex)
 for(int i =0; i< 20; i++){
    for(int j=20; j<30; j++){
        if( i>10 && j<25){
            doSomething();
        }
    }
 }
 
 
 refactoring 
 1) 모든 중첩 함수처리
 for(int i =0; i< 20; i++){
    doSomethingWithI(i);
 }
 
 private void doSomethingWihti(int i){
     for(int j=20; j<30; j++){
        doSomethingWithIJ(i, j);
     }
 }
 
 private void doSomethingWihtIJ(int i, int j){
    if( i>10 && j<25){
        doSomething();
    }
 }
 
 
````

<br />
<br />

`부정어를 대하는 자세`
````
    부정어를 사용할때도 뇌의 사고를 최소화하도록 설계 필요

    ex) 
    if(!isRightDirection() // isRightDirection메소드 명을 읽고 ! 부정어를 읽으며 2번의 사고흐름 생성
    {
        doSomething();
    }
    
    
    refactor)
    if(isNotRightDirection()
    {
        doSomething();
    }
    
    1. 부정어구를 쓰지 않아도 되는 상황인지 체크
    2. 부정의 의미를 담은 다른 단어가 존재하는지 고민
        or 부정어구로 메서드명 구성 (isNotRightDirection)
````

<br />
<br />

`해피 케이스와 예외 처리`
````
    - 예외 발생할 가능성 낮추기
    - 어떤 값의 검증이 필요한 부분은 주로 외부 세계와의 접점
        (사용자 입력, 객체 생성자, 외부 서버의 요청)
    - 의도한 예외와 예상하지 못한 예외 구분
        (사용자에게 보여줄 예외 vs 개발자가 보고 처리해야할 예외)
````

<br />
<br />

`Null을 대하는 자세`
````
    1. 항상 NullPointerException을 방지하는 방향으로 경각심 가지기
    2. 메서드 설계시 return null 자제
        -> 자제가 어렵다면 Optional 사용 고민 필요
        
    * Optional
        -> Optional은 비싼 객체다. 꼭 필요한 상황에서 반환 타입에 사용
        -> Optional을 파라미터로 받지 않도록 한다. 분기 케이스가 3개나 된다
            (Optional의 데이터가 null인지 아닌지 + Optional 자체가 Null인지)
        -> Optional을 반환받았다면 최대한 빠르게 해소
            -> isPresent .get 등 API 사용
            -> orElse(), orElseGet(), orElseThrow()의 차이를 숙지
                -> orElse() => 항상 실행, 확정된 값일때 사용
                -> orElseGet() => null인 경우 실행, 값을 제공하는 동작 정의
````

<br />
<br />
<br />
<br />

## 추상의 관점으로 바라보는 객체 지향
````
    
````

<br />
<br />

`객체 설계하기`
````
    객체가 제공해야 하는것
        - 절차 지향에서 잘 보이지 않았떤 개념을 사기화
        - 관심사가 한 군데로 ㅗㅁ이기 때문에, 유지보수성 증가
            => ex) 객체 내부에서 객체가 가진 데이터의 유효성 검증 책임을 가질 수 있따.
        - 여러 객체를 사용하는 입장에서는, 구체적인 구현에 신경 쓰지 않고,
          보다 높은 추상화 레벨에서 도메인 로직을 다룰 수 있다.
````

<br />
<br />

`새로운 객체를 만들 때 주의할 점`
````
    - 1개의 관심사로 명확하게 책임이 정의되었는지 확인
        => 메서드를 추상화할 때와 비슷하다.
        => 객체를 만듦으로써 외부 세계와 어떤 소통을 하려고 하는지 생각
        
    - setter 사용 자제
        => 데이터의 불변이 중요. 변하는 데이터라더라도 객체가 핸들링 할 수 있어야함.
        => 객체 내부에서 외부 세계의 개입 없이 자체적인 변경/가공으로 처리할 수 있는지를 확인
        => 만약 외부에서 가지고 있는 데이터로 데이터 변경 요청을 해야하는 경우,
            set~이라는 네이밍보다는 update~와 같이 의도를 드러내는 네이밍으로 고려
            
    - getter역시 "처음부터" 사용 자제
        => 게터가 필요한 시점에 추가
    
    - 필드의 수는 적을수록 좋다.
        => 불필요한 데이터가 많을수록 복잡도가 높아지고 대응할 변화가 많아진다.
        => 필드 A를 가지고 계산할 수 있는 A' 필드가 있따면 메서드 기능으로 제공
        => 단, 미리 가공하는 것이 성능 상 이점이 있다면, 필드로 가지고 있는것이 좋을수도 있다.
        
        ex) class Bill{
                private final List<Menu> menus;
                private final long totalPrice;
                
                // 필드 대신 제공할 수 있는 기능 (totalPrice를 가지고 있을 필요가 없다.)
                privaet long calculateTotalPrice(){
                    return this.menus.stream()
                                .mapToLong(Menu::getPrice)
                                .sume();
                }
        }
     
````

<br />
<br />

`단일 책임 원칙`