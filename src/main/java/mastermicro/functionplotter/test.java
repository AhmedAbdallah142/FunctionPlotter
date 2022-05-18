package mastermicro.functionplotter;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import groovy.util.Eval;

import java.util.function.Function;

public class test {
    public static void main(String[] args){
        String expression = "3^x";
        DoubleEvaluator eval = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
        variables.set("x", 2.0);
        variables.set("y", 3.0);

        Double result = eval.evaluate(expression, variables);
        System.out.println(result);
        String func2 = "x*2.0";
        Function<Double,Double> f= fromStringToFunction(func2);
        System.out.println(f.apply(2.0));
//        System.out.println(result2);
    }
    public static Function<Double, Double> fromStringToFunction(String expression) {
        return x -> (double) Eval.x(x, expression);
    }
}
