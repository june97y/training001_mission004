package com.mission_004.mission004.Repository;

import com.mission_004.mission004.Advice_Exception.AlbumNotFoundException;
import com.mission_004.mission004.Model.Album;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*@Service annotation is used in your service layer and annotates classes that perform service tasks,
often you don't use it but in many case you use this annotation to represent a best practice*/
//@Service

@Repository
public class AlbumRepository
{
    private JdbcTemplate jdbcTemplate;

    /*constructor*/
    public AlbumRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*create album*/
    public int createAlbum(Album album)
    {
        return
                jdbcTemplate.update
                        ("INSERT INTO album(id, title, artist, producer, recordLabel, releaseYear, copiesSold) values(?,?,?,?,?,?,?)",
                                album.getId(), album.getTitle(), album.getArtist(), album.getProducer(), album.getRecordLabel(), album.getReleaseYear(), album.getCopiesSold());

    }

    /*map first, read later*/
    /*row mapper - maps query results to java objects */
    public class AlbumRowMapper implements RowMapper<Album>
    {
        @Override
        public Album mapRow(ResultSet res, int rowNum) throws SQLException
        {
            Album album = new Album();

            album.setId(res.getString("id"));
            album.setTitle(res.getString("title"));
            album.setArtist(res.getString("artist"));
            album.setProducer(res.getString("producer"));
            album.setRecordLabel(res.getString("recordLabel"));
            album.setReleaseYear(res.getInt("releaseYear"));
            album.setCopiesSold(res.getInt("copiesSold"));

            return album;
        }
    }

    /*read all albums*/
    public List<Album> readAll()
    {
        return jdbcTemplate.query
                ("SELECT * FROM album",
                        (res, rowNum)->
                                new Album(
                                        res.getString("id"),
                                        res.getString("title"),
                                        res.getString("artist"),
                                        res.getString("producer"),
                                        res.getString("recordLabel"),
                                        res.getInt("releaseYear"),
                                        res.getInt("copiesSold")
                                )
                );
    }

    /*read specific album by id*/
    public Album readById(String id)
    {
        return jdbcTemplate.queryForObject
                ("SELECT * FROM album where id=?",
                        new Object[]{id},
                        new AlbumRowMapper()
                );
    }

    /*read specific album by id and title*/
    public List<Album> readByIdAndTitle(String id, String title)
    {
        return jdbcTemplate.query
                ("SELECT * FROM album WHERE id=? AND title=?",
                        new Object[]{id, title},
                        new AlbumRowMapper()
                );
    }

    /*count how many album in the album table*/
    public int countAlbum() throws SQLException, Exception
    {
        int result = 10;
        result = jdbcTemplate.queryForObject
                (
                        "SELECT COUNT(*) FROM album", Integer.class
                );
        return result;
    }

    /*update album*/
    public int updateAlbum(String searchID, Album album) throws SQLException
    {
        String sql = "UPDATE album SET id=?, title=?, artist=?, producer=?, recordLabel=?, releaseYear=?, copiesSold=? WHERE id=?";
        return jdbcTemplate.update(sql, album.getId(), album.getTitle(), album.getArtist(), album.getProducer(), album.getRecordLabel(), album.getReleaseYear(), album.getCopiesSold(), searchID);
    }

    /*delete album*/
    public int deleteAlbum(String searchId) throws AlbumNotFoundException
    {
        String sql = "DELETE FROM album WHERE id=?";
        return jdbcTemplate.update(sql, searchId);
    }


}
