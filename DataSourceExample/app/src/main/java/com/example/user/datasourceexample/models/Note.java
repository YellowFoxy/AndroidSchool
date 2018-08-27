package com.example.user.datasourceexample.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable {


    private int Id;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    private String CreatedDateTime;
    public String getCreatedDateTime(){
        return CreatedDateTime;
    }
    public void setCreatedDateTime(String date){
        CreatedDateTime=date;
    }
    public void setCreatedDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        setCreatedDateTime(sdf.format(date));
    }

    private String Header;
    public String getHeader(){
        return Header;
    }
    public void setHeader(String header) {
        Header = header;
    }

    private String Text;

    public String getText() {
        return Text;
    }
    public void setText(String text) {
        Text = text;
    }

    public Note(String header, String text) {
        Header = header;
        Text = text;
        setCreatedDateTime(new Date());
        Id = 0;
    }
}
