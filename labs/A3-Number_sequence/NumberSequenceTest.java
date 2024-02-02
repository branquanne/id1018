// NumberSequenceTest.java

/****************************************************************

NumberSequenceTest is a test program for the classes
ArrayNumberSequence and LinkedNumberSequence.

Author
Fadil Galjic

****************************************************************/
/* 
import static java.lang.System.out;

class NumberSequenceTest
{
    public static void main (String[] args)
    {
		double[] realNumbers =
		    {8.0, 2.0, 16.0, 5.0, 1.0, 12.0, 4.0};
        NumberSequence sequence = null;
        sequence = new ArrayNumberSequence(realNumbers);
        // sequence = new LinkedNumberSequence(realNumbers);
        out.println("the sequence:");
        out.println(sequence);
        out.println();

        // add code here
    }
}*/

// NumberSequenceTest.java

// NumberSequenceTest.java

public class NumberSequenceTest {
    public static void main(String[] args) {
        double[] realNumbers = { 8.0, 2.0, 16.0, 5.0, 1.0, 12.0, 4.0 };
        NumberSequence sequence = null;
        sequence = new ArrayNumberSequence(realNumbers);
        //sequence = new LinkedNumberSequence(realNumbers);
        System.out.println("the sequence:");
        System.out.println(sequence);
        System.out.println();

        //add code here

        // Check the methods defined in the NumberSequence interface
        System.out.println("length: " + sequence.length());
        System.out.println("one upper bound: " + sequence.upperBound());
        System.out.println("one lower bound: " + sequence.lowerBound());
        System.out.println("number at position 3: " + sequence.numberAt(3));
        System.out.println("position of 8.0: " + sequence.positionOf(8.0));
        System.out.println("is increasing: " + sequence.isIncreasing());
        System.out.println("is decreasing: " + sequence.isDecreasing());
        System.out.println("contains 16.0: " + sequence.contains(16.0));

        // Additional operations
        System.out.println("\nadd 32.0:");
        sequence.add(32.0);
        System.out.println(sequence);

        System.out.println("insert 0.0 at position 7:");
        sequence.insert(7, 0.0);
        System.out.println(sequence);

        System.out.println("remove at position 7:");
        sequence.removeAt(7);
        System.out.println(sequence);

        System.out.println("\ncorresponding array:");
        double[] array = sequence.asArray();
        for (double num : array) {
            System.out.print(num + " ");
        }
    }
}
