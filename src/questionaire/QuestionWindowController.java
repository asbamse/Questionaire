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
public class QuestionWindowController implements Initializable 
{
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
    public void initialize(URL url, ResourceBundle rb) 
    {
        //TO-DO
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
        updateScore();
    }
    
    private void updateScore()
    {
        int score = 0;
        
        for (int i = 0; i < grpList.size(); i++) 
        {
            ToggleGroup tg = grpList.get(i);
            
            //In case of nullPointer skip step of iteration.
            if(tg.getSelectedToggle() == null)
            {
                continue;
            }
            
            //Get selected RadioButton and change score regarding to case. 
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
     * Adds the new participant, answers and score to the the main window.
     * Closes the QuestionaireWindow.
     * @param event
     */
    @FXML
    private void handleSave(ActionEvent event) 
    {
        ArrayList<String> answers = new ArrayList();
        
        //Get answers
        for (ToggleGroup tg : grpList) 
        {
            RadioButton rb = (RadioButton)tg.getSelectedToggle();
            
            if(tg.getSelectedToggle() == null)
            {
                answers.add("Neutral");
                continue;
            }
            
            switch(rb.getText())
            {
                case "Agree":
                    answers.add("Agree");
                    break;
                case "Neutral":
                    answers.add("Neutral");
                    break;
                case "Disagree":
                    answers.add("Disagree");
                    break;
                default:
                    answers.add("Neutral");
                    break;
            }
        }
        
        //Add to list in MainWindowController and close window.
        mvc.addToList(lblName.getText(), answers, lblScore.getText());
        Stage stage = (Stage) lblName.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Sets the MainWindowController.
     * @param m The MainWindowController which generated the window.
     */
    public void setMvc(MainWindowController m) 
    {
        mvc = m;
    }
    
    public void setQuestions(ArrayList<String> arr)
    {
        setQuestions(arr, null);
    }
    
    /**
     * Creates a new row for each element in array.
     * @param arr array of questions.
     */
    public void setQuestions(ArrayList<String> arr, ArrayList<String> selected)
    {
        int count = 0;
        
        //Iterate through all questions.
        for (String string : arr) 
        {
            //In case the a row for index already exists.
            if(lblList.size() > count)
            {
                lblList.get(count).setText(string);
            }
            else
            {
                //Creates a new label with current index.
                Label lblTmp = new Label(string);
                lblTmp.setId("lbl" + count);
                
                //Create new ToggleGroup for RadioButtons.
                ToggleGroup grpTmp = new ToggleGroup();
                
                //Create RadioButton with wanted settings.
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
                
                //In case selected is set.
                if(selected != null)
                {
                    //Set right RadioButton to selected
                    switch(selected.get(arr.indexOf(string)))
                    {
                        case "Agree":
                            rb3.setSelected(true);
                            break;
                        case "Neutral":
                            rb2.setSelected(true);
                            break;
                        case "Disagree":
                            rb1.setSelected(true);
                            break;
                        default:
                            break;
                    }
                }
                
                //Add everything to ArrayLists and GridPane.
                lblList.add(lblTmp);
                grpList.add(grpTmp);
                grid.addRow(count, lblTmp);
                grid.getRowConstraints().add(grid.getRowConstraints().get(0));
                grid.add(rb1, 1, count);
                grid.add(rb2, 1, count);
                grid.add(rb3, 1, count);                
            }
            count++;
        }
        updateScore();
    }
}
