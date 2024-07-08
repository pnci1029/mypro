package udemy.inventory;

public class main {
    public static void main(String[] args) {
        InventoryCounter inventoryCounter = new InventoryCounter();

        IncreamentThread increamentThread = new IncreamentThread(inventoryCounter);
        DecreamentThread decreamentThread = new DecreamentThread(inventoryCounter);
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
