package udemy.inventoryV3;

/**
 * 스레드간 동기화처리를 위한 두번째 방법
 * lock 객체를 통한 각각의 임계 영역 생성

 * 메소드 전체를 synchronized할 필요가 없고
 * 동기화 임계 영역의 코드는 최소화 하는게 좋다
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();

        IncreamentThread increamentThread = new IncreamentThread(inventoryCounter);
        DecreamentThread decreamentThread = new DecreamentThread(inventoryCounter);

        increamentThread.start();
        increamentThread.join();

        decreamentThread.start();
        decreamentThread.join();
        System.out.println("increamentThread = " + increamentThread);

        increamentThread.start();
        decreamentThread.start();

        increamentThread.join();
        decreamentThread.join();
        System.out.println("increamentThread = " + increamentThread);

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

        Object lock = new Object();


        public void increase() {
            synchronized (this.lock) {
                items++;
            }
        }

        public void decrease() {
            synchronized (this.lock) {
                items--;
            }
        }

        public int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}
