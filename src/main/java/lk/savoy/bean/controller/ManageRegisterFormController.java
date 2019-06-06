package lk.savoy.bean.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.savoy.bean.business.custom.ActorBO;
import lk.savoy.bean.business.custom.ActorMovieBO;
import lk.savoy.bean.business.custom.MovieBO;
import lk.savoy.bean.dto.ActorDTO;
import lk.savoy.bean.dto.ActorMovieDTO;
import lk.savoy.bean.dto.MovieDTO;
import lk.savoy.bean.main.AppInitializer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageRegisterFormController {
    public ImageView imgHome;
    public JFXButton btnAddNew;
    public JFXComboBox cmbMovieID;
    public JFXTextField txtMovieName;
    public JFXComboBox cmbActorID;
    public JFXTextField txtActorName;
    public JFXTextField txtRole;
    public JFXButton btnRegister;
    public AnchorPane registerPane;


    private MovieBO movieBO = AppInitializer.ctx.getBean(MovieBO.class);
    private ActorBO actorBO = AppInitializer.ctx.getBean(ActorBO.class);
    private ActorMovieBO actorMovieBO = AppInitializer.ctx.getBean(ActorMovieBO.class);

    public void initialize() {


        // Loading all Movie Ids
        try {
            List<MovieDTO> allMovies = movieBO.getAllMovies();
            for (MovieDTO movie : allMovies) {
                cmbMovieID.getItems().add(movie.getId());
            }
        } catch (Exception e) {
            Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
        }

        cmbMovieID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object movieId) {

                try {
                    if (movieId == null)
                        return;

                    MovieDTO movie = movieBO.getMovieById((Integer) movieId);
                    txtMovieName.setText(movie.getName());

                } catch (Exception e) {
                    Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
                }

            }
        });

        // Loading all Actor Ids
        try {
            List<ActorDTO> allActors = actorBO.getAllActors();
            for (ActorDTO actor : allActors) {
                cmbActorID.getItems().add(actor.getId());
            }
        } catch (Exception e) {
            Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
        }

        cmbActorID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object actorId) {
                try {
                    if (actorId == null)
                        return;

                    ActorDTO actor = actorBO.getActorById((Integer) actorId);
                    txtActorName.setText(actor.getName());
                } catch (Exception e) {
                    Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
                }
            }
        });

    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (cmbMovieID.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Movie ID is empty", ButtonType.OK).showAndWait();
            cmbMovieID.requestFocus();
            return;
        } else if (cmbActorID == null) {
            new Alert(Alert.AlertType.ERROR, "Actor ID is empty", ButtonType.OK).showAndWait();
            cmbActorID.requestFocus();
            return;
        }

        ActorMovieDTO actorMovieDTO = new ActorMovieDTO(Integer.parseInt(cmbActorID.getValue().toString()), Integer.parseInt(cmbMovieID.getValue().toString()), txtRole.getText());
        try {
            actorMovieBO.saveActorMovie(actorMovieDTO);

            new Alert(Alert.AlertType.INFORMATION, "Actor and Movie has been saved successfully", ButtonType.OK).showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void backToHome(MouseEvent event) throws IOException {
        AppInitializer.backToHome(registerPane, (Stage) this.registerPane.getScene().getWindow());
    }


}
