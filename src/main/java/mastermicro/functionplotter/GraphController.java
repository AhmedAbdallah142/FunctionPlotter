package mastermicro.functionplotter;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.function.Function;
import groovy.util.Eval;
import javafx.scene.control.TextField;

public class GraphController {
    @FXML
    private Label Message;
    @FXML
    private TextField Equation;
    @FXML
    private TextField MinX;
    @FXML
    private TextField MaxX;
    @FXML
    private TextField nPoints;
    @FXML
    private LineChart<NumberAxis,NumberAxis> lineChart;

    @FXML
    protected void Plot() {
        plotFunction(Equation.getText(),Double.parseDouble(MinX.getText()),Double.parseDouble(MaxX.getText()),Integer.parseInt(nPoints.getText()));
    }
    @FXML
    protected void Clear() {
        Message.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {

    }

    public static Function<Double, Double> fromStringToFunction(String expression) {
        return x -> (double) Eval.x(x, expression);
    }

    public void plotFunction(String function,double MinX, double MaxX,int nPoint) {
        XYChart.Series<NumberAxis,NumberAxis> series = new XYChart.Series<>();
        series.setName(function);

        DoubleEvaluator eval = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
//        GraphicsContext gc = canvas.getGraphicsContext2D();
        variables.set("x", MinX);
        variables.set("X", MinX);
        series.getData().add(new  XYChart.Data(MinX, eval.evaluate(function, variables)));
        for (double x = MinX; x <= MaxX; x+=(MaxX-MinX)/nPoint) {
            variables.set("x", x);
            variables.set("X", x);
            series.getData().add(new  XYChart.Data(x, eval.evaluate(function, variables)));
//            gc.lineTo(x,eval.evaluate(function, variables));
        }
        lineChart.getData().add(series);
//        gc.stroke();
    }

    public void plotFunction(Function<Double, Double> function,double MinX, double MaxX,int nPoint) {
//        Canvas plot = new Canvas();
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.moveTo(MinX, function.apply(MinX));
//        for (double x = MinX; x <= MaxX; x+=(MaxX-MinX)/nPoint) {
//            gc.lineTo(x, function.apply(x));
//        }
//        gc.stroke();
    }

}