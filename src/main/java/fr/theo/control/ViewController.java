
package fr.theo.control;

import fr.theo.data.table.Piece;
import fr.theo.data.table.Band;
import fr.theo.data.table.Meeting;
import fr.theo.data.table.Speciality;
import fr.theo.data.table.Member;
import fr.theo.data.table.Instrument;
import fr.theo.data.table.Place;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.util.regex.Pattern;

public class ViewController {

    @FXML private TextField pieceDurationTextField;
    @FXML private TextField bandCountTextField;

    @FXML private ChoiceBox<Speciality> specialityChoiceBox;
    @FXML private ChoiceBox<Instrument> instrumentChoiceBox;

    @FXML private TableView<Piece> pieceTableView;
    @FXML private TableColumn<Piece, String> pieceTitleColumn;
    @FXML private TableColumn<Piece, String> pieceAuthorColumn;
    @FXML private TableColumn<Piece, String> pieceDurationColumn;

    @FXML private TableView<Band> bandTableView;
    @FXML private TableColumn<Band, String> bandNameColumn;
    @FXML private TableColumn<Band, String> bandCorrespondentColumn;

    @FXML private TableView<Member> memberTableView;
    @FXML private TableColumn<Member, String> memberNameColumn;
    @FXML private TableColumn<Member, String> memberInstrumentColumn;

    @FXML private TableView<Meeting> meetingTableView;
    @FXML private TableColumn<Meeting, String> meetingNameColumn;
    @FXML private TableColumn<Meeting, Integer> meetingVisitorsColumn;

    @FXML private TableView<Place> placeTableView;
    @FXML private TableColumn<Place, String> placeCityColumn;
    @FXML private TableColumn<Place, String> placeCountryColumn;

    @FXML void researchAction(ActionEvent event) {research();}
    @FXML void refreshAction(ActionEvent event) {refresh();}

    @FXML public void initialize() {
      setupColumns();
      setupChoiceBoxes();
      setupEventHandlers();
      setInitialState();
    }

    private void refresh() {setInitialState();}
    
    private void research() {
      String strBandCount = bandCountTextField.getText();
      if (Pattern.matches("[0-9]", strBandCount)) {
        int bandCount = Integer.parseInt(strBandCount);
        meetingTableView.setItems(
          Controller.getDatabase()
            .getMeetingsByBandCount(bandCount)
        );
      }
    }

    private void setInitialState() {
      pieceTableView.setItems(Controller.getDatabase().getAllPieces());
      bandTableView.setItems(Controller.getDatabase().getAllBands());
      meetingTableView.setItems(Controller.getDatabase().getAllMeetings());
      placeTableView.setItems(Controller.getDatabase().getAllPlaces());
      memberTableView.setItems(Controller.getDatabase().getAllMusicians());
    }

    private void setupEventHandlers() {

      pieceTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
          Piece piece = pieceTableView.getSelectionModel().getSelectedItem();
          if (piece != null) {
            bandTableView.setItems(
              Controller.getDatabase()
                .getBandsByPiece(piece.getId())
            );
          }
        }
      });

      bandTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
          Band band = bandTableView.getSelectionModel().getSelectedItem();
          if (band != null) {
            Piece piece = pieceTableView.getSelectionModel().getSelectedItem();
            if (piece != null) {
              meetingTableView.setItems(
                Controller.getDatabase()
                  .getMeetingsByPieceAndBand(piece.getId(), band.getId())
              );
            }
          }
        }
      });

      meetingTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
          Meeting meeting = meetingTableView.getSelectionModel().getSelectedItem();
          if (meeting != null) {
            Speciality speciality = specialityChoiceBox.getValue();
            if (speciality != null) {
              memberTableView.setItems(
                Controller.getDatabase()
                  .getMusicianByMeetingAndSpeciality(
                    meeting.getId(), 
                    speciality.getId()
                  )
              );
            }
          }
        }
      });

      placeTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
          Place place = placeTableView.getSelectionModel().getSelectedItem();
          if (place != null) {
            String strDuration = pieceDurationTextField.getText();
            if (Pattern.matches("[0-9][0-9]:[0-9][0-9]", strDuration)) {
              String[] splittedText = strDuration.split(":", 0);
              int minimumDuration = Integer.parseInt(splittedText[0]) * 60 
                                    + Integer.parseInt(splittedText[1]);
              pieceTableView.setItems(
                Controller.getDatabase()
                  .getPiecesByCountryLongerThan(place.getCountryId(), minimumDuration)
              );
            }
          }
        }
      });
    }

    private void setupChoiceBoxes() {
      specialityChoiceBox.getItems().addAll(
        Controller.getDatabase().getAllSpecialities()
      );
      instrumentChoiceBox.getItems().addAll(
        Controller.getDatabase().getAllInstruments()
      );
    }

    private void setupColumns() {
      pieceTitleColumn.setCellValueFactory(
        new PropertyValueFactory<Piece, String>("title")
      );
      pieceAuthorColumn.setCellValueFactory(
        new PropertyValueFactory<Piece, String>("author")
      );
      pieceDurationColumn.setCellValueFactory(
        new PropertyValueFactory<Piece, String>("duration")
      );
      bandNameColumn.setCellValueFactory(
        new PropertyValueFactory<Band, String>("name")
      );
      bandCorrespondentColumn.setCellValueFactory(
        new PropertyValueFactory<Band, String>("correspondent")
      );
      memberNameColumn.setCellValueFactory(
        new PropertyValueFactory<Member, String>("name")
      );
      memberInstrumentColumn.setCellValueFactory(
        new PropertyValueFactory<Member, String>("instrument")
      );
      meetingNameColumn.setCellValueFactory(
        new PropertyValueFactory<Meeting, String>("name")
      );
      meetingVisitorsColumn.setCellValueFactory(
        new PropertyValueFactory<Meeting, Integer>("visitors")
      );
      placeCityColumn.setCellValueFactory(
        new PropertyValueFactory<Place, String>("cityName")
      );
      placeCountryColumn.setCellValueFactory(
        new PropertyValueFactory<Place, String>("countryName")
      );
    }
}









