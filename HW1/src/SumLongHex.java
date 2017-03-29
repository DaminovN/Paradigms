/**
 * Created by HP on 15.02.2017.
 */
public class SumLongHex {
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 0; i < args.length; i++) {
            String value = "";
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isWhitespace(args[i].charAt(j)) || j + 1 == args[i].length()) {
                    if (!Character.isWhitespace(args[i].charAt(j))) {
                        value += args[i].charAt(j);
                    }

                    if (value.length() > 0) {
                        value = value.toLowerCase();
                        if (value.length() > 2 && value.charAt(0) == '0' && value.charAt(1) == 'x') {
                            value = value.substring(2, value.length());
                            sum += Long.parseUnsignedLong(value, 16);
                        } else if (value.charAt(0) != '-') {
                            sum += Long.parseUnsignedLong(value);
                        } else {
                            sum += Long.parseLong(value);
                        }
                        value = "";
                    }
                } else {
                    value += args[i].charAt(j);
                }
            }
        }
        System.out.println(sum);
    }
}
