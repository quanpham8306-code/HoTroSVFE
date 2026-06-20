package org.example.Controller.Student;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.Note;
import org.example.Service.NoteService;
import org.example.Util.SceneUtil;

import java.time.LocalDate;

public class NoteController {
    @FXML private Button btnHome;
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnSubject;
    @FXML private Button btnLogout;

    @FXML private TextArea txtNote;
    @FXML private TextField txtTag;
    @FXML private TextField txtTitle;
    @FXML private TextField txtSearch;

    @FXML private TableView<Note> noteTable;
    @FXML private TableColumn<Note,LocalDate> colDate;
    @FXML private TableColumn<Note, String> colTag;
    @FXML private TableColumn<Note, String> colTitle;

    private final NoteService noteService = new NoteService();

    @FXML public void initialize() {
        clearNote();
        setUpTable();
        getNoteTable();
        loadTable();
    }
    @FXML
    public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Score.fxml");
    }

    @FXML
    public void showSchedule() {
        SceneUtil.switchScene(btnSchedule, "/fxml/Schedule.fxml");
    }

    @FXML
    public void showHome() {
        SceneUtil.switchScene(btnHome, "/fxml/Home.fxml");
    }

    @FXML
    public void showSubject() {
        SceneUtil.switchScene(btnSubject, "/fxml/VirtualSchedule.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }
    @FXML public void newNote() {
        clearNote();
    }
    @FXML public void cancel(){
        clearNote();
    }
    @FXML public void seeAll(){
        noteTable.getItems().clear();
        noteTable.getItems().addAll(noteService.findAllNotes());
    }

    @FXML public void findNote(){
        String title = txtSearch.getText();
        noteTable.getItems().clear();
        noteTable.getItems().addAll(noteService.findNoteByTitle(title));
    }
    @FXML public void deleteNote(){
        String title = txtSearch.getText();
        noteService.deleteNote(noteTable.getSelectionModel().getSelectedItem());
    }
    @FXML public void saveNote(){
        Note note = getNote();
        noteService.saveNote(note);
    }
    private void getNoteTable()
    {
        Note note = noteTable.getSelectionModel()
                .selectedItemProperty()
                .get();
        loadNote(note);
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
    private void loadNote(Note note){
        clearNote();
        txtNote.setText(note.getNote());
        txtTag.setText(note.getTag());
        txtTitle.setText(note.getTitle());
    }
}
