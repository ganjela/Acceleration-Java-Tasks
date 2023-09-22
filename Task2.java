public class Task2 {

    private static final int[] COINS = {50, 20, 10, 5, 1};

    public int minSplit(int amount) {

        int min = 0;

        for (int value : COINS) {
            int count = amount / value;
            min += count;
            amount = amount - count * value;
        }

        return min;

    }
}
