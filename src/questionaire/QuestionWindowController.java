/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
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
    private Label lblName;
    @FXML
    private Label lblScore;
    @FXML
    private Button btnCalc;
    @FXML
    private ArrayList<ToggleGroup> grpList;
    
    private MainWindowController mvc;
    @FXML
    private ArrayList<Label> lblList;
    @FXML
    private Label lbl0;
    @FXML
    private GridPane grid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Change the text of lblName to the parameter.
     * @param name New text
     */
    public void changeName(String name)
    {
        lblName.setText(name);
    }
    
    /**
     * Generates a sum of all ToggleGroups selected value and sets the lblScore to the result.
     * @param event
     */
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

    /**
     * Adds the new participant and score to the list in the main window.
     * Closes the QuestionaireWindow.
     * @param event
     */
    @FXML
    private void handleSave(ActionEvent event) {
        mvc.addToList(lblName.getText(), lblScore.getText());
        Stage stage = (Stage) lblName.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Sets the MainWindowController.
     * @param m The MainWindowController which generated the window.
     */
    public void setMvc(MainWindowController m) {
        mvc = m;
    }
    
    /**
     * Creates a new row for each element in array.
     * @param arr array of questions.
     */
    public void setQuestions(ArrayList<String> arr)
    {
        int count = 0;
        for (String string : arr) {
            if(lblList.size() > count)
            {
                lblList.get(count).setText(string);
            }
            else
            {
                Label lblTmp = new Label(string);
                lblTmp.setId("lbl" + count);
                lblList.add(lblTmp);
                
                ToggleGroup grpTmp = new ToggleGroup();
                
                RadioButton rb1 = new RadioButton("Disagree");
                rb1.setToggleGroup(grpTmp);
                rb1.setMnemonicParsing(false);
                rb1.setOnAction(e -> HandleScore(e));
                GridPane.setHalignment(rb1, HPos.LEFT);
                
                RadioButton rb2 = new RadioButton("Neutral");
                rb2.setToggleGroup(grpTmp);
                rb2.setMnemonicParsing(false);
                rb2.setOnAction(e -> HandleScore(e));
                GridPane.setHalignment(rb2, HPos.CENTER);
                
                RadioButton rb3 = new RadioButton("Agree");
                rb3.setToggleGroup(grpTmp);
                rb3.setMnemonicParsing(false);
                rb3.setOnAction(e -> HandleScore(e));
                GridPane.setHalignment(rb3, HPos.RIGHT);
                
                grpList.add(grpTmp);
                grid.addRow(count, lblTmp);
                grid.getRowConstraints().add(grid.getRowConstraints().get(0));
                grid.add(rb1, 1, count);
                grid.add(rb2, 1, count);
                grid.add(rb3, 1, count);                
            }
            count++;
        }
    }
}
