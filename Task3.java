import java.util.TreeSet;
public class Task3 {

    public int notContains(int[] array) {
        TreeSet<Integer> sortedSet = new TreeSet<>();

        for (int n : array) {
            if (n > 0) sortedSet.add(n);
        }

        int min = 0;
        int previous = 0;

        for (Integer n : sortedSet) {
            if (previous + 1 != n) {
                min = previous + 1;
                return min;
            } else {
                previous = n;
            }
        }

        min = previous + 1;
        return min;
    }
}
