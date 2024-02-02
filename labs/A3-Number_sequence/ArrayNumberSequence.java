// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

import java.util.Arrays;

public class ArrayNumberSequence implements NumberSequence {
    private double[] numbers;

    public ArrayNumberSequence(double[] numbers) {
        if (numbers.length < 2)
            throw new IllegalArgumentException("not a sequence");

        this.numbers = deepCopy(numbers);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numbers.length - 1; i++)
            s.append(numbers[i]).append(", ");
        s.append(numbers[numbers.length - 1]);

        return s.toString();
    }

    //add code here

    private double[] deepCopy(double[] original) {
        return Arrays.copyOf(original, original.length);
    }

    public int length() {
        return numbers.length;
    }

    public double upperBound() {
        return Arrays.stream(numbers).max().orElse(Double.NaN);
    }

    public double lowerBound() {
        return Arrays.stream(numbers).min().orElse(Double.NaN);
    }

    public double elementAt(int position) {
        if (position < 0 || position >= numbers.length) {
            throw new IllegalArgumentException("Invalid position");
        }
        return numbers[position];
    }

    public int positionOf(double number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public boolean isIncreasing() {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] >= numbers[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isDecreasing() {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] <= numbers[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(double number) {
        for (double num : numbers) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    public void add(double number) {
        numbers = Arrays.copyOf(numbers, numbers.length + 1);
        numbers[numbers.length - 1] = number;
    }

    public void insert(int position, double number) {
        if (position < 0 || position > numbers.length) {
            throw new IllegalArgumentException("Invalid position");
        }
        double[] newArray = new double[numbers.length + 1];
        System.arraycopy(numbers, 0, newArray, 0, position);
        newArray[position] = number;
        System.arraycopy(numbers, position, newArray, position + 1, numbers.length - position);
        numbers = newArray;
    }

    @Override
    public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
        if (position < 0 || position >= length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (length() <= 2) {
            throw new IllegalStateException("There must be more than two numbers in the sequence.");
        }

        double[] newNumbers = new double[numbers.length - 1];
        System.arraycopy(numbers, 0, newNumbers, 0, position);
        System.arraycopy(numbers, position + 1, newNumbers, position, numbers.length - position - 1);
        numbers = newNumbers;
    }

    public double numberAt(int position) {
        if (position < 0 || position >= numbers.length) {
            throw new IllegalArgumentException("Invalid position");
        }
        return numbers[position];
    }

    public double[] asArray() {
        return Arrays.copyOf(numbers, numbers.length);
    }

}
