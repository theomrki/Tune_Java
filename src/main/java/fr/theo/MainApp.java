
package fr.theo;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

import fr.theo.util.fxml.Loader;

public class MainApp extends Application {

  @Override
  public void start(Stage s) throws IOException {
    ((Stage) Loader.loadFXML("login")).show();
  }

  public static void main(String[] args) {
      launch(args);
  }

}
