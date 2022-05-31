package cli;

import java.util.Arrays;

import static io.fries.koans.KoanAssert.__;

class MainKoans {

    // This is the main method of a Java program.
    // Different classes can hold a main method, creating different entry-point to your program (which is not a
    // recommended practice).
    public static void main(final String[] args) {
        // The 'args' parameter contains the arguments passed to your program.
        // You can edit them in you IDE run configuration.
        final String programArguments = Arrays.toString(args);

        // System.out is the standard output stream of any Java program.
        System.out.println("This is a standard output message: " + programArguments);

        // System.err is the standard error stream of any Java program.
        System.err.println("This is a standard error message: " + programArguments);

        // Use System.exit to return an arbitrary status code to your operating system.
        // If no System.exit call is specified, the default status code is 0.
        System.exit((int) 0);
    }
}
