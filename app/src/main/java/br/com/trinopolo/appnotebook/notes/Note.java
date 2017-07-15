package br.com.trinopolo.appnotebook.notes;

import java.util.Date;

/**
 * Created by marconvcm on 14/07/2017.
 */

public class Note {

    private int id;
    private String title;
    private String body;
    private Date createAt;
    private int status;

    public Note(int id, String title, String body, Date createAt) {

        this.id = id;
        this.title = title;
        this.body = body;
        this.createAt = createAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getTitle() + "\n" + getCreateAt().toString();
    }
}
