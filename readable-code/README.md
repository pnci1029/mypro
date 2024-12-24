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