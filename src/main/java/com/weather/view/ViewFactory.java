package com.weather.view;

import com.weather.controller.BaseController;
import com.weather.controller.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private static final int MAIN_WINDOW_HEIGHT = 740;
    private static final int MAIN_WINDOW_WIDTH = 420;

    public void showMainWindow() {
        BaseController controller = new MainViewController(this, "MainView.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.class.getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinHeight(MAIN_WINDOW_HEIGHT);
        stage.setMinWidth(MAIN_WINDOW_WIDTH);
        stage.setMaxHeight(MAIN_WINDOW_HEIGHT);
        stage.setMaxWidth(MAIN_WINDOW_WIDTH);
        stage.show();
    }
}
