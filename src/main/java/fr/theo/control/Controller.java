
package fr.theo.control;

import fr.theo.data.base.ATuneOfJavaDatabase;

public class Controller {

  private static ATuneOfJavaDatabase database;

  public static void openDatabase(
    String host,
    String port,
    String name,
    String user,
    String password
  ) {
    database = new ATuneOfJavaDatabase(host, port, name, user, password);
  }

  public static void closeDatabase() {
    database.closeConnection();
  }

  public static ATuneOfJavaDatabase getDatabase() {return database;}
}
