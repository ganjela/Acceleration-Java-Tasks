import java.util.HashSet;

public class Task1 {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }

        Integer[] target = set.toArray(new Integer[0]);

        return target[0];
    }
}
