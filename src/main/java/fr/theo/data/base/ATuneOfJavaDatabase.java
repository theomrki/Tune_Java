
package fr.theo.data.base;

import java.util.ArrayList;

import fr.theo.util.sql.connection.MySQLConnectionWrapper;

import fr.theo.data.table.Piece;
import fr.theo.data.table.Band;
import fr.theo.data.table.Instrument;
import fr.theo.data.table.Meeting;
import fr.theo.data.table.Speciality;
import fr.theo.data.table.Member;
import fr.theo.data.table.Place;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ATuneOfJavaDatabase {

  private MySQLConnectionWrapper connection;

  public ATuneOfJavaDatabase(
    String host,
    String port,
    String database,
    String user,
    String password
  ) {
    this.connection = new MySQLConnectionWrapper(host, port, database);
    this.connection.open(user, password);
  }

  public void closeConnection() {this.connection.close();}

  public ObservableList<Member> getAllMusicians() {
    ObservableList<Member> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure("get_all_musicians");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      String instrument = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_musician": id = Integer.parseInt(field[1]);
          case "member": name = field[1]; break;
          case "denomination": instrument = field[1]; break;
          default:
            System.out.println(field[0]);
            break;
        }
      }
      output.add(new Member(id, name, instrument));
    }
    return output;
  }

  public ObservableList<Place> getAllPlaces() {
    ObservableList<Place> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure("get_all_places");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int city_id = -1;
      int country_id = -1;
      String city_name = "";
      String country_name = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_city": city_id = Integer.parseInt(field[1]); break;
          case "id_country": country_id = Integer.parseInt(field[1]); break;
          case "city_name": city_name = field[1]; break;
          case "country_name": country_name = field[1]; break;
          default: System.out.println(column); break;
        }
      }
      output.add(new Place(city_id, country_id, city_name, country_name));
    }
    return output;
  }

  public ObservableList<Piece> getAllPieces() {
    ObservableList<Piece> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure("get_all_pieces");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String title = "";
      String author = "";
      String duration = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_piece" : id = Integer.parseInt(field[1]); break;
          case "denomination": title = field[1]; break;
          case "author": author = field[1]; break;
          case "duration": 
            int d = Integer.parseInt(field[1]);
            int q = d / 60;
            int r = d % 60;
            duration = String.format(
              "%s%d:%s%d", 
              q < 10 ? "0": "", q,
              r < 10 ? "0": "", r
            ); 
            break;
        }
      }
      output.add(new Piece(id, title, author, duration));
    }
    return output;
  }

  public ObservableList<Piece> getPiecesByCountryLongerThan(int id_country, int minimumDuration) {
    ObservableList<Piece> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure(
      "get_piece_by_country_longer_than",
      String.format("%d", id_country),
      String.format("%d", minimumDuration)
    );
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String title = "";
      String author = "";
      String duration = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_piece" : id = Integer.parseInt(field[1]); break;
          case "denomination": title = field[1]; break;
          case "author": author = field[1]; break;
          case "duration": 
            int d = Integer.parseInt(field[1]);
            int q = d / 60;
            int r = d % 60;
            duration = String.format(
              "%s%d:%s%d", 
              q < 10 ? "0": "", q,
              r < 10 ? "0": "", r
            ); 
            break;
        }
      }
      output.add(new Piece(id, title, author, duration));
    }
    return output;
  }
  
  public ObservableList<Band> getAllBands() {
    ObservableList<Band> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure("get_all_bands");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      String correspondent = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_band": id = Integer.parseInt(field[1]); break;
          case "denomination": name = field[1]; break;
          case "correspondent": correspondent = field[1]; break;
          default:
            System.out.println(field[0]);
            break;
        }
      }
      output.add(new Band(id, name, correspondent));
    }
    return output;
  }

  public ObservableList<Meeting> getAllMeetings() {
    ObservableList<Meeting> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure("get_all_meetings");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      int visitors = -1;
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_meeting": id = Integer.parseInt(field[1]); break;
          case "label": name = field[1]; break;
          case "expected_visitors": visitors = Integer.parseInt(field[1]); break;
          default:
            System.out.println(field[0]);
            break;
        }
      }
      output.add(new Meeting(id, name, visitors));
    }
    return output;
  }

  public ObservableList<Speciality> getAllSpecialities() {
    ObservableList<Speciality> output = FXCollections.observableArrayList();
    String[] result = this.connection.SelectAll("speciality");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String label = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_speciality": id = Integer.parseInt(field[1]); break;
          case "denomination": label = field[1]; break;
          default: break;
        }
      }
      output.add(new Speciality(id, label));
    }
    return output;
  }

    public ObservableList<Instrument> getAllInstruments() {
    ObservableList<Instrument> output = FXCollections.observableArrayList();
    String[] result = this.connection.SelectAll("instrument");
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String label = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_instument": id = Integer.parseInt(field[1]); break;
          case "denomination": label = field[1]; break;
          default: break;
        }
      }
      output.add(new Instrument(id, label));
    }
    return output;
  }

  public ObservableList<Member> getMusicianByMeetingAndSpeciality(int id_meeting, int id_speciality) {
    ObservableList<Member> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure(
      "get_musician_by_meeting_and_speciality",
      String.format("%d", id_meeting),
      String.format("%d", id_speciality)
    );
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      String instrument = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_musician": id = Integer.parseInt(field[1]);
          case "member": name = field[1]; break;
          case "denomination": instrument = field[1]; break;
          default:
            System.out.println(field[0]);
            break;
        }
      }
      output.add(new Member(id, name, instrument));
    }
    return output;
  }

  public ObservableList<Band> getBandsByPiece(int piece_id) {
    ObservableList<Band> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure(
      "get_bands_playing_piece",
      String.format("%d", piece_id)
    );
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      String correspondent = "";
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_band": id = Integer.parseInt(field[1]);
          case "denomination": name = field[1]; break;
          case "correspondent": correspondent = field[1]; break;
          default:
            System.out.println(field[0]);
            break;
        }
      }
      output.add(new Band(id, name, correspondent));
    }
    return output;
  }

  public ObservableList<Meeting> getMeetingsByPieceAndBand(int piece_id, int band_id) {
    ObservableList<Meeting> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure(
      "get_meetings_by_piece_and_band",
      String.format("%d", piece_id),
      String.format("%d", band_id)
    );
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      int visitors = -1;
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_meeting": id = Integer.parseInt(field[1]);
          case "label": name = field[1]; break;
          case "expected_visitors": visitors = Integer.parseInt(field[1]); break;
          default:
            break;
        }
      }
      output.add(new Meeting(id, name, visitors));
    }
    return output;
  }

  public ObservableList<Meeting> getMeetingsByBandCount(int bandCount) {
    ObservableList<Meeting> output = FXCollections.observableArrayList();
    ArrayList<String> result = this.connection.callProcedure(
      "get_meetings_by_band_count",
      String.format("%d", bandCount)
    );
    for (String row: result) {
      String[] columns = row.split(",", 0);
      int id = -1;
      String name = "";
      int visitors = -1;
      for (String column: columns) {
        String[] field = column.split(":", 0);
        switch (field[0]) {
          case "id_meeting": id = Integer.parseInt(field[1]);
          case "label": name = field[1]; break;
          case "expected_visitors": visitors = Integer.parseInt(field[1]); break;
          default:
            break;
        }
      }
      output.add(new Meeting(id, name, visitors));
    }
    return output;
  }
}








