/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Asbamz
 */
public class GraphWindowController implements Initializable {

    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private LineChart<?, ?> questionChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TO-DO
    }    
    
    public void addData(ArrayList<String> questions, ArrayList<Result> results)
    {
        //Chart container
        XYChart.Series series = new XYChart.Series();
        
        //For each question
        for (int i = 0; i < questions.size(); i++) 
        {
            //We are going to work in doubles because the average wound be integers.
            double count = 0.0;
            double sum = 0.0;
            
            //For all results
            for (Result result : results) 
            {
                //Sum up answer
                switch(result.getAnswers().get(i))
                {
                    case "Agree":
                        sum++;
                        break;
                    case "Neutral":
                        break;
                    case "Disagree":
                        sum--;
                        break;
                    default:
                        break;
                }
                
                count++;
            }
            
            //Add the point if any.
            if(count > 0)
            {
                series.getData().add(new XYChart.Data(questions.get(i), sum/count));
            }
        }
        
        //Add it to the chart.
        questionChart.getData().addAll(series);
    }
    
}
