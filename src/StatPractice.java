import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StatPractice {
    public static void main(String[] args){
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty()){
            double x = StdIn.readDouble();
            numbers.add(x);
        }
        double sum = 0.0;
        int n = numbers.size();
        for (double number : numbers)
            sum += number;
        double mean = sum/n;
        sum = 0.0;
        for (double number : numbers)
            sum += (number - mean) * (number - mean);
        double stdev = Math.sqrt(sum/(n-1));
        StdOut.printf("Mean  %.2f\n", mean);
        StdOut.printf("Stdev  %.2f\n", stdev);
    }
}
