import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        System.out.println("Sum: " + add("1,2,3,4"));
    }
    
    static int add(String numbers)
    {
        int sum = numbers.isEmpty() ? 0 : 
                  Arrays.stream(numbers.split(","))  // Split the string by ";"
                        .mapToInt(Integer::parseInt) // Convert each part to an integer
                        .sum(); // Sum up the integers

        return sum;
        
    }
}
