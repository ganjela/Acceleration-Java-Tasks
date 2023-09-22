public class Task4 {

    public String binarySum(String a, String b) {

        StringBuilder binary = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int temp = 0;

        while (i >= 0 || j >= 0) {
            int sum = temp;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
            }
            binary.append(sum % 2);
            temp = sum / 2;

            i--;
            j--;
        }

        if (temp > 0) {
            binary.append(temp);
        }

        return binary.reverse().toString();
    }
}
