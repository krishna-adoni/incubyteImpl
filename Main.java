import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SumUsingStreams {
    public static void main(String[] args) {
        String input = "//;\n1;2;-3;4;-5"; // Example input with negative numbers

        try {
            System.out.println("Sum: " + calculateSum(input));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int calculateSum(String input) {
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

        // Convert the numbers to integers
        List<Integer> parsedNumbers = Arrays.stream(numbers.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Find negative numbers
        List<Integer> negativeNumbers = parsedNumbers.stream()
                .filter(n -> n < 0)
                .collect(Collectors.toList());

        // If there are any negative numbers, throw an exception
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
        }

        // Return the sum of valid numbers
        return parsedNumbers.stream().mapToInt(Integer::intValue).sum();
    }
}
