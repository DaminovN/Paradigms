public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            for (String value: args[i].split("\\p{javaWhitespace}")) {
                if (!value.isEmpty()) {
                    sum += Integer.parseInt(value);
                }
            }
        }
        System.out.println(sum);
    }
}
