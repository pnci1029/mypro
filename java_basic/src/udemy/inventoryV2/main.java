package udemy.inventoryV2;

/**
 * 스레드간 동기화처리를 위한 첫번째 방법 synchronized
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

        public synchronized void increase() {
            items++;
        }

        public synchronized void decrease() {
            items--;
        }

        public synchronized int getItems() {
            return items;
        }
    }
}
