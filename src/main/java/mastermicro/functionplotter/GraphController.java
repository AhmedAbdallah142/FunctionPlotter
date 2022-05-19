package mastermicro.functionplotter;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
        try {
            Message.setText("");
            plotFunction(Equation.getText(),Double.parseDouble(MinX.getText()),Double.parseDouble(MaxX.getText()),Integer.parseInt(nPoints.getText()));
        }catch (Exception e){
            Message.setText(e.getMessage());
        }
    }
    @FXML
    protected void Clear() {
        Message.setText("");
        lineChart.getData().clear();
    }

    public void plotFunction(String function,double MinX, double MaxX,int nPoint) {
        if (MaxX < MinX)
            throw new RuntimeException("Max X must be greater than Min X");
        if((nPoint < 1)||(nPoint > 5000))
            throw new RuntimeException("Number of points must be between 1 and 5000");

        XYChart.Series<NumberAxis,NumberAxis> series = new XYChart.Series<>();
        series.setName(function);
        DoubleEvaluator eval = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        variables.set("x", MinX);
        variables.set("X", MinX);
        series.getData().add(new  XYChart.Data(MinX, eval.evaluate(function, variables)));
        for (double x = MinX; x < MaxX; x+=(MaxX-MinX)/nPoint) {
            variables.set("x", x);
            variables.set("X", x);
            series.getData().add(new  XYChart.Data(x, eval.evaluate(function, variables)));
        }
        variables.set("x", MaxX);
        variables.set("X", MaxX);
        series.getData().add(new  XYChart.Data(MaxX, eval.evaluate(function, variables)));
        lineChart.getData().add(series);
    }
}