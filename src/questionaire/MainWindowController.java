/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
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
    
    private ObservableList<String> participants = FXCollections.observableArrayList();
    
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
        
        
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listP.setItems(participants);
    }
    
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
