package lk.savoy.bean.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainFormController {
    public ImageView imgMovie;
    public ImageView imgLogo;
    public ImageView imgActor;
    public ImageView imgRegister;
    public Label lbl1;
    public Label lbl2;
    public AnchorPane root;

    public void navigate(MouseEvent event) throws Exception {

        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgMovie":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageMovieForm.fxml"));
                    break;
                case "imgActor":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageActorForm.fxml"));
                    break;
                case "imgRegister":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageRegisterForm.fxml"));
                    break;
            }
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
            }
        }
    }
}
