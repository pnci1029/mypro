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