package org.example.Controller.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.LopHocPhan;
import org.example.Model.Note;
import org.example.Service.Student.NoteService;
import org.example.Util.AlertUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NoteController {
    @FXML private Button btnHome;
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnSubject;
    @FXML private Button btnSupport;
    @FXML private Button btnLogout;

    @FXML private TextArea txtNote;
    @FXML private TextField txtTag;
    @FXML private TextField txtTitle;
    @FXML private TextField txtSearch;

    @FXML private TableView<Note> noteTable;
    @FXML private TableColumn<Note,LocalDate> colDate;
    @FXML private TableColumn<Note, String> colTag;
    @FXML private TableColumn<Note, String> colTitle;

    private Note selectedNote;
    private final NoteService noteService = new NoteService();
    private ObservableList<Note> noteList = FXCollections.observableArrayList();

    @FXML public void initialize() {
        selectedNote = null;
        clearNote();
        setUpTable();
        getNoteTable();
        loadTable();
        noteList.addAll(noteService.findAllNotes());
        txtSearch.textProperty()
                .addListener((obs, oldV, newV)
                        -> search(newV));
        noteTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (newValue == null) return;
                    selectedNote = newValue;
                });
    }
    @FXML
    public void showScore() throws IOException {
        SceneUtil.switchScene(btnScore, "/fxml/Student/Score.fxml");
    }

    @FXML
    public void showSchedule() throws IOException {
        SceneUtil.switchScene(btnSchedule, "/fxml/Student/Schedule.fxml");
    }

    @FXML
    public void showHome() throws IOException {
        SceneUtil.switchScene(btnHome, "/fxml/Student/Home.fxml");
    }

    @FXML
    public void showSubject() throws IOException {
        SceneUtil.switchScene(btnSubject, "/fxml/Student/VitualSchedule.fxml");
    }

    @FXML
    public void showSupport() throws IOException {
        SceneUtil.switchScene(btnSupport, "/fxml/Student/Support.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }
    @FXML public void newNote() {
        clearNote();
    }
    @FXML public void cancel(){
        clearNote();
    }
    @FXML public void seeAll(){
        noteTable.getItems().clear();
        try {
            noteTable.getItems().addAll(noteService.findAllNotes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void findNote(){
        String title = txtSearch.getText();
        noteTable.getItems().clear();
        noteTable.getItems().addAll(noteService.findNoteByTitle(title));
    }
    @FXML
    public void deleteNote() {
        if (selectedNote == null) {
            return;
        }
        noteService.deleteNote(selectedNote);

        clearNote();
        loadTable();

    }
    @FXML
    public void saveNote() {

        if (txtTitle.equals("")) {
            AlertUtil.showAlert("Title không được để trống");
            return;
        }
        if (txtNote.equals("")) {
            AlertUtil.showAlert("Nội dung không được để trống");
            return;
        }
        Note note = getNote();
        noteService.saveNote(note);
        clearNote();
        loadTable();
    }
    private void getNoteTable()
    {
        noteTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        loadNote(newValue);
                    }
                });
    }
    private void setUpTable() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    }
    private void loadTable() {
        seeAll();
    }
    private  void clearNote(){
        txtNote.clear();
        txtTag.clear();
        txtTitle.clear();
    }
    private Note getNote(){
        Note note = new Note();
        note.setDate(LocalDate.now());
        note.setNote(txtNote.getText());
        note.setTag(txtTag.getText());
        note.setTitle(txtTitle.getText());
        return note;
    }
    private void loadNote(Note note) {
        if (note == null) {
            clearNote();
            return;
        }

        txtNote.setText(note.getNote());
        txtTag.setText(note.getTag());
        txtTitle.setText(note.getTitle());
    }
    private void search(String keyword) {

        FilteredList<Note> filtered =
                new FilteredList<>(noteList);

        filtered.setPredicate(note -> {

            if (keyword == null
                    || keyword.isBlank())
                return true;

            String key =
                    keyword.toLowerCase();

            return note.getTitle()
                    .toLowerCase()
                    .contains(key);
        });

        noteTable.setItems(filtered);
    }
}
