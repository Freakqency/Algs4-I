import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class EvaluatePractice {
    static boolean isOperand(String op){
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("sqrt");
    }

    static Double performOperation(double op1, double op2, String op){
        switch (op) {
            case "+":
                return op1 + op2;
            case "-":
                return op2 - op1;
            case "*":
                return op2 * op1;
            case "/":
                return op2 / op1;
            default:
                return Math.sqrt(op1);
        }
    }

    public static void main(String[] args){
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("("))
                continue;
            else if (isOperand(s))
                ops.push(s);
            else if (s.equals(")")){
                String op = ops.pop();
                Double op1 = vals.pop();
                Double op2 = vals.pop();
                Double res = performOperation(op1, op2, op);
                vals.push(res);
            }
            else
                vals.push(Double.parseDouble(s));

        }
        StdOut.println(vals.pop());

    }
}
