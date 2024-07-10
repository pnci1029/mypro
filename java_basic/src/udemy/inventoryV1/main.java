package udemy.inventoryV1;

public class main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();

        IncreamentThread increamentThread = new IncreamentThread(inventoryCounter);
        DecreamentThread decreamentThread = new DecreamentThread(inventoryCounter);

        /**
          10000 - 10000이기 때문에 0 도출
         */
        increamentThread.start();
        increamentThread.join();

        decreamentThread.start();
        decreamentThread.join();
        System.out.println("increamentThread = " + increamentThread);

        /**
         10000 - 10000이기 때문에 0이 예상되나
         매번 결과가 달라짐 -165...
         */
        increamentThread.start();
        decreamentThread.start();

        increamentThread.join();
        decreamentThread.join();
        System.out.println("increamentThread = " + increamentThread);


        /**
         그 이유는 스레드간 리소스 공유 때문인데,

         increament실제 작업은
         1. 현재 counter에 값 할당
         2. 새로운 값 counter + 1 할당
         3. items = 새로운값 = counter + 1
         순으로 이어진다.

         그러나 위 3개가 순차적으로 진행되지않는 케이스가 발생한다.
         ex)

         increament                             decreament
         1. current = 0
         2. newVal = current +1
                                                3. current = 0
                                                4. newVal = current -1
                                                5. items = newVal = -1
         6. items = newVal = 1

         OS 스레드의 스케줄링의 차이에 따라 이전 값이 정의되지않고 처리될수있음
         특히 작업량이 많아질수록 잠재적인 차이가 더 커진다.
         */
    }

    // 재고 10000개 추가
    public static class IncreamentThread extends Thread{
        private InventoryCounter inventoryCounter;

        public IncreamentThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increase();
            }
        }
    }

    // 재고 10000개 감소
    public static class DecreamentThread extends Thread{
        private InventoryCounter inventoryCounter;


        public DecreamentThread(InventoryCounter increamentThread) {
            this.inventoryCounter = increamentThread;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrease();
            }
        }
    }

    private static class InventoryCounter{
        private int items = 0;;

        public void increase() {
            items++;
        }

        public void decrease() {
            items--;
        }

        public int getItems() {
            return items;
        }
    }
}
