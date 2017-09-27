/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
    @FXML
    private ArrayList<ToggleGroup> grpList;
    
    private MainWindowController mvc;
    
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
        for (int i = 0; i < grpList.size(); i++) {
            ToggleGroup tg = grpList.get(i);
            
            if(tg.getSelectedToggle() == null)
            {
                continue;
            }
            
            RadioButton rb = (RadioButton)tg.getSelectedToggle();
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

    @FXML
    private void handleSave(ActionEvent event) {
        mvc.addToList(lblName.getText(), lblScore.getText());
        Stage stage = (Stage) lblName.getScene().getWindow();
        stage.close();
    }
    
    public void setMvc(MainWindowController m){
        mvc = m;
    }
}
