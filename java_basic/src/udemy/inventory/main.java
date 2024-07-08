package udemy.inventory;

public class main {
    public static void main(String[] args) {

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
