import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        System.out.println("Sum: " + add("//;\n1;2"));
    }
    
    public static int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        String delimiter = "[,;\n]"; // Default delimiters: comma, semicolon, newline
        String numbers = input;

        // Check for a custom delimiter at the beginning of the input
        Pattern pattern = Pattern.compile("//(.)\n(.*)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            delimiter = Pattern.quote(matcher.group(1)); // Extract and escape the custom delimiter
            numbers = matcher.group(2); // Extract the actual numbers part
        }

        return Arrays.stream(numbers.split(delimiter))
                     .mapToInt(Integer::parseInt)
                     .sum();
    }
}
