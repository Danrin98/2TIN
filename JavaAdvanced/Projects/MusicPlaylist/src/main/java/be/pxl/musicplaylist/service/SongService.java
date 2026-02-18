package be.pxl.musicplaylist.service;

import be.pxl.musicplaylist.api.Genre;
import be.pxl.musicplaylist.api.Song;
import be.pxl.musicplaylist.exception.SongNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    List<Song> songs = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(SongService.class);


    public Song createSong(Song song) {
        songs.add(song);
        return song;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Song> getSongsByGenre(Genre genre) {

        return songs.stream()
                .filter(song -> song.getGenre() == genre)
                .collect(Collectors.toList());

//        List<Song> newList = new ArrayList<>();
//        for(Song song : songs) {
//            if (song.getGenre().equals(genre)) {
//                newList.add(song);
//            }
//        }
//        return newList;
    }

    public Song updateSong(int index, Song song) {
        if (index < 0) throw new InvalidParameterException("index is negative");

        try {
            songs.set(index, song);
            return song;
        } catch (IndexOutOfBoundsException e) {
            logger.warn("Index out of bounds exception");
            throw new SongNotFoundException("Index not found", e);
        }
    }

    public void deleteSong(int index) {
        songs.remove(index);
    }
}
