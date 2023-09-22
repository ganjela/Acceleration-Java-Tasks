public class Task2 {

    public int minSplit(int amount) {

        int[] COINS = {50, 20, 10, 5, 1};
        int min = 0;

        for (int value : COINS) {
            int count = amount / value;
            min += count;
            amount = amount - count * value;
        }

        return min;

    }
}
