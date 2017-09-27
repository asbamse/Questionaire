/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class QuestionWindowController implements Initializable {

    @FXML
    private ToggleGroup grp0;
    @FXML
    private ToggleGroup grp1;
    @FXML
    private ToggleGroup grp2;
    @FXML
    private ToggleGroup grp3;
    @FXML
    private ToggleGroup grp4;
    @FXML
    private ToggleGroup grp5;
    @FXML
    private ToggleGroup grp6;
    @FXML
    private ToggleGroup grp7;
    
    private ToggleGroup[] toggleGroup = {grp0, grp1, grp2, grp3, grp4, grp5, grp6, grp7};
    @FXML
    private Label lblName;
    @FXML
    private Label lblScore;
    @FXML
    private Button btnCalc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void changeName(String name)
    {
        lblName.setText(name);
    }

    @FXML
    private void HandleScore(ActionEvent event) 
    {
        int score = 0;
        for (int i = 0; i < toggleGroup.length; i++) 
        {    
            RadioButton rb = (RadioButton)toggleGroup[i].getSelectedToggle();
            switch(rb.getText())
            {
                case "Agree":
                    score++;
                    break;
                case "Neutral":
                    break;
                case "Disagree":
                    score--;
                    break;
                default:
                    break;
            }
        }
        lblScore.setText(String.valueOf(score));
                
    }
    
}
