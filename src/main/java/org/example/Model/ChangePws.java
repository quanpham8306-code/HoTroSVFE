package org.example.Model;

public class ChangePws {
    private String oldPws;
    private String newPws;

    public ChangePws() {
    }

    public ChangePws(String oldPws, String newPws) {
        this.oldPws = oldPws;
        this.newPws = newPws;
    }

    public String getOldPws() {
        return oldPws;
    }

    public void setOldPws(String oldPws) {
        this.oldPws = oldPws;
    }

    public String getNewPws() {
        return newPws;
    }

    public void setNewPws(String newPws) {
        this.newPws = newPws;
    }
}
