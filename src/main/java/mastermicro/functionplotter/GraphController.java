package mastermicro.functionplotter;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * the controller class used to control with fxml
 * interact with user input
 * @author Ahmed Abdallah
 */
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

    /**
     * called when the plot button is pressed
     * collecting and validating user input
     * show error message to user
     */
    @FXML
    protected void Plot() {
        try {
            Message.setText("");
            plotFunction(Equation.getText(),Double.parseDouble(MinX.getText()),Double.parseDouble(MaxX.getText()),Integer.parseInt(nPoints.getText()));
        }catch (Exception e){
            Message.setText(e.getMessage());
        }
    }

    /**
     * called when the clear button is pressed
     * clearing user inputs and charts drawn
     */
    @FXML
    protected void Clear() {
        lineChart.getData().clear();
        Message.setText("");
        MaxX.setText("");
        MinX.setText("");
        nPoints.setText("");
    }

    /**
     * validate given parameters
     * evaluate the given function at the period from min x to max x with rate of n points
     * @param function the required to plot
     * @param MinX the smallest value of x
     * @param MaxX the max value of x
     * @param nPoint the total number of points to drawn
     */
    public void plotFunction(String function,double MinX, double MaxX,int nPoint) {
        if (MaxX < MinX)
            throw new RuntimeException("Min X greater");
        if((nPoint < 1)||(nPoint > 5000))
            throw new RuntimeException("nPoints 1:5000");
        XYChart.Series<NumberAxis,NumberAxis> series = new XYChart.Series<>();
        series.setName(function);
        DoubleEvaluator eval = new DoubleEvaluator();
        Double temp;
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        for (Double x = MinX; x <= MaxX; x+=(MaxX-MinX)/nPoint) {
            variables.set("x", x);
            variables.set("X", x);
            temp = eval.evaluate(function, variables);
            if(temp.isInfinite())
                throw new RuntimeException("Reached Infinity");
            series.getData().add(new  XYChart.Data(x, temp));
        }
        variables.set("x", MaxX);
        variables.set("X", MaxX);
        temp = eval.evaluate(function, variables);
        if(temp.isInfinite())
            throw new RuntimeException("Reached Infinity");
        series.getData().add(new  XYChart.Data(MaxX, temp));
        lineChart.getData().add(series);
    }
}