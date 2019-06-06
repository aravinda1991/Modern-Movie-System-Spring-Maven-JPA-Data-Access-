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
import lk.savoy.bean.business.custom.MovieBO;
import lk.savoy.bean.dto.MovieDTO;
import lk.savoy.bean.main.AppInitializer;
import lk.savoy.bean.util.MovieTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageMovieFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtMovieName;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public ImageView imgHome;
    public JFXButton btnAddNew;
    public TableView<MovieTM> tblMovie;
    public AnchorPane movieAnchorPane;

    private MovieBO movieBO = AppInitializer.ctx.getBean(MovieBO.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblMovie.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMovie.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));

        txtId.setDisable(true);
        txtMovieName.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        try {
            List<MovieDTO> allMovies = movieBO.getAllMovies();
            for (MovieDTO movie : allMovies) {
                tblMovie.getItems().add(new MovieTM(movie.getId(), movie.getName()));
            }
        } catch (Exception e) {
            Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
        }

        tblMovie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MovieTM>() {
            @Override
            public void changed(ObservableValue<? extends MovieTM> observable, MovieTM oldValue, MovieTM selectedMovie) {

                if (selectedMovie == null) {
                    return;
                }

                txtId.setText(String.valueOf(selectedMovie.getId()));
                txtMovieName.setText(selectedMovie.getName());

                txtId.setEditable(false);

                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtMovieName.setDisable(false);
            }
        });

    }

    // Save
    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (txtId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Movie ID is empty", ButtonType.OK).showAndWait();
            txtId.requestFocus();
            return;
        } else if (txtMovieName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Movie Name is empty", ButtonType.OK).showAndWait();
            txtMovieName.requestFocus();
            return;
        }

        ///////////////////////////////////////////////////////////////////////
        if (tblMovie.getSelectionModel().isEmpty()) {

            MovieDTO movieDTO = new MovieDTO(Integer.parseInt(txtId.getText()), txtMovieName.getText());
            try {
                movieBO.saveMovie(movieDTO);
                new Alert(Alert.AlertType.INFORMATION, "Movie has been saved successfully", ButtonType.OK).showAndWait();

                MovieTM movieTM = new MovieTM(Integer.parseInt(txtId.getText()), txtMovieName.getText());
                tblMovie.getItems().add(movieTM);
                tblMovie.scrollTo(movieTM);

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the movie, try again", ButtonType.OK).showAndWait();
                Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
            }

        } else {
            // Update
            try {
                movieBO.updateMovie(new MovieDTO(Integer.parseInt(txtId.getText()), txtMovieName.getText()));
                new Alert(Alert.AlertType.INFORMATION, "Movie has been updated successfully").show();

                MovieTM selectedMovie = tblMovie.getSelectionModel().getSelectedItem();
                selectedMovie.setName(txtMovieName.getText());
                tblMovie.refresh();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the movie, try again").show();
                Logger.getLogger("lk.savoy.bean.controller").log(Level.SEVERE, null, e);
            }
        }
        reset();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this movie?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            int selectedRow = tblMovie.getSelectionModel().getSelectedIndex();

            try {
                movieBO.removeMovie(Integer.parseInt(txtId.getText()));
                tblMovie.getItems().remove(tblMovie.getSelectionModel().getSelectedItem());
                reset();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the movie, try again").show();
                Logger.getLogger("lk.savoy,bean.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    private void reset() {
        txtId.clear();
        txtMovieName.clear();
        txtId.requestFocus();
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        tblMovie.getSelectionModel().clearSelection();
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtId.requestFocus();
        btnSave.setDisable(false);
        txtId.setDisable(false);
        txtMovieName.setDisable(false);

    }

    public void backToHome(MouseEvent event) throws Exception {
        AppInitializer.backToHome(movieAnchorPane, (Stage) this.movieAnchorPane.getScene().getWindow());
    }
}
