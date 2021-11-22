
package fr.theo.util.fxml;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class Loader {
  public static <T> T loadFXML(String file) throws IOException {
    return (
      new FXMLLoader(Loader.class.getResource("/fxml/"+ file + ".fxml"))
    ).load();
  }
}