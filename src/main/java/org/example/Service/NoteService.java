package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.MonHoc;
import org.example.Model.Note;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class NoteService {
    private final ApiClient apiClient = new ApiClient();

    public Note findNoteByTitle(String title){
        String response = apiClient.get(ApiEndpoint.STUDENT_NOTE_TITLE + title);
        return ApiResponseHandler.readData(response, Note.class);
    }
    public List<Note> findAllNotes() {
        String response = apiClient.get(ApiEndpoint.STUDENT_NOTE_ME);
        return ApiResponseHandler.readData(response, new TypeReference<List<Note>>() {});
    }
    public void deleteNote(Note note){
        String response = apiClient.get(ApiEndpoint.STUDENT_NOTE_DELETE);
    }
    public void saveNote(Note note){
        String response = apiClient.get(ApiEndpoint.STUDENT_NOTE_POST);
        apiClient.post(ApiEndpoint.STUDENT_NOTE_POST, note);
    }
    public void updateNote(Note note){
        String response = apiClient.get(ApiEndpoint.STUDENT_NOTE_UPDATE);
        apiClient.post(ApiEndpoint.STUDENT_NOTE_UPDATE, note);
    }
}
