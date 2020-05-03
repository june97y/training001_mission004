package com.mission_004.mission004.Model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Album
{
    @Id private String id;
    private String title;
    private String artist;
    @Nullable private String producer;
    @Nullable private String recordLabel;
    @Nullable private int releaseYear;
    private int copiesSold;

    public Album(String id, String title, String artist, String producer, String recordLabel, int releaseYear, int copiesSold)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.producer = producer;
        this.recordLabel = recordLabel;
        this.releaseYear = releaseYear;
        this.copiesSold = copiesSold;
    }

    public String toString()
    {
        return "Album {" +
                "id=" + id +
                "title=" + title +
                "artist=" + artist +
                "producer=" + producer +
                "recordLabel=" + recordLabel +
                "releaseYear=" + releaseYear +
                "copiesSold=" + copiesSold +
                "}";
    }

    /*static?*/
    static Album create(String title, String artist, String producer, String recordLabel, int releaseYear, int copiesSold)
    {
        return new Album(null, title, artist, producer, recordLabel, releaseYear, copiesSold);
    }
}

/*
spring data jdbc will be in snake case,
so this table name will be 'album'
if attribute is albumName, the column name is 'album_name'
*/
