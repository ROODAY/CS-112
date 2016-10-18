public class Test {
    public static void main(String[] args) {
        int count = 0;
        int n = 10;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
            for (int k = 0; k < j; k++)
            count++;
        System.out.println(count);
    }
}