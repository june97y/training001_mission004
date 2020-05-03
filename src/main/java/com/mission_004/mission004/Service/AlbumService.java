package com.mission_004.mission004.Service;

import com.mission_004.mission004.Advice_Exception.AlbumNotFoundException;
import com.mission_004.mission004.Model.Album;
import com.mission_004.mission004.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
/*@Service annotation is used in your service layer and annotates classes that perform service tasks,
often you don't use it but in many case you use this annotation to represent a best practice*/
public class AlbumService
{
    @Autowired //marks constructor field, setter field to be autowired by spring dependency injection
    private AlbumRepository albumRepository;

    /*create album*/
    public int createAlbum(Album album)
    {
        return albumRepository.createAlbum(album);
    }

    /*read all album*/
    public List<Album> readAllAlbum()
    {
        return albumRepository.readAll();
    }

    /*read specific album by id*/
    public Album readById(String id)
    {
        return albumRepository.readById(id);
    }

    /*read specific album by id and title*/
    public List<Album> readByIdAndTitle(String searchId, String title)
    {
        return albumRepository.readByIdAndTitle(searchId, title);
    }

    /*count how many albums in the album table*/
    public int countAlbum() throws Exception
    {
        return albumRepository.countAlbum();
    }

    /*update album*/
    public int updateAlbum(String searchId, Album tempAlbum) throws SQLException
    {
        return albumRepository.updateAlbum(searchId, tempAlbum);
    }

    /*delete Album*/
    public int deleteAlbum(String searchId) throws AlbumNotFoundException
    {
        return albumRepository.deleteAlbum(searchId);
    }
}
