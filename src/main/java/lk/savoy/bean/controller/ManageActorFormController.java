package lk.savoy.bean.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.savoy.bean.business.custom.ActorBO;
import lk.savoy.bean.dto.ActorDTO;
import lk.savoy.bean.main.AppInitializer;
import lk.savoy.bean.util.ActorTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageActorFormController implements Initializable {
    public ImageView imgHome;
    public JFXButton btnAddNew;
    public JFXTextField txtActorID;
    public JFXTextField txtActorName;
    public JFXTextField txtActorAge;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<ActorTM> tblActor;
    public AnchorPane actorPane;

    private ActorBO actorBO = AppInitializer.ctx.getBean(ActorBO.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblActor.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblActor.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblActor.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("age"));

        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        try {
            List<ActorDTO> allActors = actorBO.getAllActors();
            for (ActorDTO actor : allActors) {
                tblActor.getItems().add(new ActorTM(actor.getId(), actor.getName(), actor.getAge()));
            }
        } catch (Exception e) {
            Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
        }


        tblActor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ActorTM>() {
            @Override
            public void changed(ObservableValue<? extends ActorTM> observable, ActorTM oldValue, ActorTM selectedActor) {

                if (selectedActor == null) {
                    return;
                }

                txtActorID.setText(String.valueOf(selectedActor.getId()));
                txtActorName.setText(selectedActor.getName());
                txtActorAge.setText(String.valueOf(selectedActor.getAge()));

                txtActorID.setEditable(false);

                btnSave.setDisable(false);
                btnDelete.setDisable(false);
            }
        });

    }

    // Save
    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (txtActorID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Actor ID is empty", ButtonType.OK).showAndWait();
            txtActorID.requestFocus();
            return;
        } else if (txtActorName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Actor Name is empty", ButtonType.OK).showAndWait();
            txtActorName.requestFocus();
            return;
        } else if (txtActorAge.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Actor Age is empty", ButtonType.OK).showAndWait();
            txtActorAge.requestFocus();
            return;
        }

        //////////////////////////////////////////////////////////
        if (tblActor.getSelectionModel().isEmpty()) {

           /* ObservableList<ActorTM> items = tblActor.getItems();
            for (ActorTM actorTM : items) {
                if (actorTM.getId().equals(txtActorID.getText())) {

                }

            }*/

            ActorDTO actorDTO = new ActorDTO(Integer.parseInt(txtActorID.getText()), txtActorName.getText(), Integer.parseInt(txtActorAge.getText()));
            try {
                actorBO.saveActor(actorDTO);
                new Alert(Alert.AlertType.INFORMATION, "Actor has been saved successfully", ButtonType.OK).showAndWait();

                ActorTM actorTM = new ActorTM(Integer.parseInt(txtActorID.getText()), txtActorName.getText(), Integer.parseInt(txtActorAge.getText()));
                tblActor.getItems().add(actorTM);
                tblActor.scrollTo(actorTM);

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the actor, try again", ButtonType.OK).showAndWait();
                Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
            }


        } else {
            // Update
            try {
                actorBO.updateActor(new ActorDTO(Integer.parseInt(txtActorID.getText()), txtActorName.getText(), Integer.parseInt(txtActorAge.getText())));
                new Alert(Alert.AlertType.INFORMATION, "Actor has been updated successfully").show();

                ActorTM selectedActor = tblActor.getSelectionModel().getSelectedItem();
                selectedActor.setName(txtActorName.getText());
                selectedActor.setAge(Integer.parseInt(txtActorAge.getText()));
                tblActor.refresh();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the actor, try again").show();
                Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
            }
        }
        reset();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this actor?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            int selectedRow = tblActor.getSelectionModel().getSelectedIndex();

            try {
                actorBO.removeActor(Integer.valueOf(txtActorID.getText()));
                tblActor.getItems().remove(tblActor.getSelectionModel().getSelectedItem());
                reset();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the actor, try again").show();
                Logger.getLogger("lk.savoy,bean.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    private void reset() {
        txtActorID.clear();
        txtActorName.clear();
        txtActorAge.clear();
        txtActorID.requestFocus();
        txtActorID.setEditable(true);
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        tblActor.getSelectionModel().clearSelection();
    }

    public void btnAddNewActor_OnAction(ActionEvent actionEvent) {
        txtActorID.requestFocus();
        btnSave.setDisable(false);

    }

    public void backToHome(MouseEvent event) throws IOException {
        AppInitializer.backToHome(actorPane, (Stage) this.actorPane.getScene().getWindow());
    }
}

