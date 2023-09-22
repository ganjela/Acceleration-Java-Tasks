public class Task5 {
    public int countVariants(int stairsCount) {
        int[] dp = new int[stairsCount + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= stairsCount; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[stairsCount];
    }
}
