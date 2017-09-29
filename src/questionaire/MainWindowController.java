/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Asbamz
 */
public class MainWindowController implements Initializable 
{    
    @FXML
    private Button button;
    @FXML
    private TextField txtNameField;
    @FXML
    private ListView<String> listP;
    
    private ArrayList<String> questions = new ArrayList();
    private ObservableList<String> participants = FXCollections.observableArrayList();
    private ArrayList<Result> results = new ArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        listP.setOnMouseClicked(this::handle);
        
        //questions.add("I think programming is fun");
        questions.add("I like to program in Greenfoot");
        questions.add("I think Java is fun");
        questions.add("Java is my preferred language");
        questions.add("My assignments are fun");
        questions.add("My assignments are too easy");
        questions.add("I always do my assignments");
        questions.add("I always attend class");
        questions.add("I understand what is going on in class");
        questions.add("I think programming is fun");
        
        listP.setItems(participants);
    }
    
    /**
     * Opens a QuestionaireWindow and sets the participant label to the given name.
     * The MainWindowController is passed so that the addToList method is accessible.
     * Add the questions.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException 
    {
        // If the Name TextField is empty it shows an alert window.
        if(txtNameField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Write a name!");
            return;
        }
        
        //Initialize Stage
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(
        getClass().getResource("QuestionWindow.fxml"));
        Parent root = fxLoader.load();
       
        //Get controller and setup content.
        QuestionWindowController cont = fxLoader.getController();
        cont.changeName(txtNameField.getText());
        cont.setMvc(this);
        cont.setQuestions(questions);
        
        //Show it
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }
    
    /**
     * Adds new participant and score. If the participant already exists it is overwritten.
     * @param name Name of participant.
     * @param answers Answers in string array ["Agree", "Agree", "Disagree"].
     * @param score Score.
     */
    public void addToList(String name, ArrayList<String> answers, String score)
    {
        //Check if the participant already commited something.
        for (String participant : participants) 
        {
            if (participant.split(":")[0].equals(name))
            {
                results.set(participants.indexOf(participant), new Result(name, answers, score));
                participants.set(participants.indexOf(participant), name + ":" + score);
                return;
            }
        }
        //Add new participant.
        participants.add(name + ":" + score);
        results.add(new Result(name, answers, score));
    }
    
    /**
     * OnMouseClicked event.
     * @param event 
     */
    public void handle(MouseEvent event) 
    {
        //Executed on doubleclick
        if (event.getClickCount() == 2) 
        {
            //Get item which was doublecliked.
            int indexSelected = listP.getItems().indexOf(listP.getSelectionModel().getSelectedItem());
            
            //Is only executed if the an item was doubleclicked. 
            if(indexSelected != -1)
            {
                //Initiate Stage
                Stage newStage = new Stage();
                newStage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fxLoader = new FXMLLoader(
                getClass().getResource("QuestionWindow.fxml"));
                Parent root;
                
                //Try catch because the handle method does not throw exceptions.
                try {
                    root = fxLoader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    //In case the FXML does not load stop execution.
                    return;
                }
                
                //Setup content.
                QuestionWindowController cont = fxLoader.getController();
                cont.changeName(results.get(indexSelected).getName());
                cont.setMvc(this);
                cont.setQuestions(questions, results.get(indexSelected).getAnswers());
                
                //Show window.
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                newStage.showAndWait();
            }
        }
    }

    @FXML
    private void handleGraph(ActionEvent event) throws IOException 
    {
        //Initialize Stage
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(
        getClass().getResource("GraphWindow.fxml"));
        Parent root = fxLoader.load();
       
        //Get controller and setup content.
        GraphWindowController cont = fxLoader.getController();
        cont.addData(questions, results);
        
        //Show it
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }
}
