package com.mission_004.mission004.Controller;

import com.mission_004.mission004.Advice_Exception.AlbumNotFoundException;
import com.mission_004.mission004.Model.Album;
import com.mission_004.mission004.Service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@EnableAutoConfiguration
@RestController
/* specialized version of controller (@Controller + @ResponseBody)*/
public class AlbumController
{
    @Autowired
    AlbumService albumService;//creating an instance of AlbumRepository so that its function can be used

    /*create album*/
    @PostMapping("/Album")
    public String create(@RequestBody Album album)
    {
        String temp = "";
        int result = 0;

        try
        {
            result = albumService.createAlbum(album);
        }
        catch(Exception e)
        {
            temp = "Error code: " + result + "\nError message: " + e + "\nEnd";
        }

        return temp;
    }

    /*read all album*/
    @GetMapping("/Album")
    @ResponseBody
    public List<Album> readAllAlbum()
    {
        return albumService.readAllAlbum();
    }

    /*read specific album by id*/
    @GetMapping("/Album/{id}")
    @ResponseBody
    public Album readById(@PathVariable(value = "id") String searchId)
    {
        return albumService.readById(searchId);
    }

    /*read specific album by id and title*/
    @GetMapping("/Album/{id}/{title}")
    @ResponseBody
    public List<Album> readByIdAndTitle(@PathVariable(value = "id") String searchId, @PathVariable(value = "title") String title)
    {
        return albumService.readByIdAndTitle(searchId, title);
    }

    /*count how many albums in the album table*/
    @GetMapping("/Album/count")
    @ResponseBody
    public int countAlbum() throws Exception
    {
        return albumService.countAlbum();
    }

    /*update album*/
    @PutMapping("/Album/{id}")
    @ResponseBody
    public int updateAlbum(@PathVariable(value = "id") String searchId, @RequestBody Album tempAlbum) throws SQLException
    {
        return albumService.updateAlbum(searchId, tempAlbum);
    }

    /*delete Album*/
    @DeleteMapping("/Album/{id}")
    @ResponseBody
    public int deleteAlbum(@PathVariable(value = "id") String searchId) throws AlbumNotFoundException
    {
        return albumService.deleteAlbum(searchId);
    }
}


