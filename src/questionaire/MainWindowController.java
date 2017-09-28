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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Asbamz
 */
public class MainWindowController implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private TextField txtNameField;
    @FXML
    private ListView<String> listP;
    
    private ArrayList<String> questions = new ArrayList();
    private ObservableList<String> participants = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(
            getClass().getResource("QuestionWindow.fxml"));
        Parent root = fxLoader.load();
       
        QuestionWindowController cont = fxLoader.getController();
        cont.changeName(txtNameField.getText());
        cont.setMvc(this);
        cont.setQuestions(questions);
        
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }
    
    /**
     * Adds new participant and score. If the participant already exists it is overwritten.
     * @param str Name of participant.
     * @param score Score.
     */
    public void addToList(String str, String score)
    {
        for (String participant : participants) {
            if (participant.split(":")[0].equals(str))
            {
                participants.set(participants.indexOf(participant), str + ":" + score);
                return;
            }
            
        }
        participants.add(str + ":" + score);
    }

    
}
