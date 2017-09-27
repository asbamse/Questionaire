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
        RadioButton rb = (RadioButton)grp0.getSelectedToggle();
        switch(rb.getText())
        {
            case "Agree":
                lblScore.setText("1");
                break;
            case "Neutral":
                lblScore.setText("0");
                break;
            case "Disagree":
                lblScore.setText("-1");
                break;
            default:
                lblScore.setText("Error");
                break;
        }
                
    }
    
}
