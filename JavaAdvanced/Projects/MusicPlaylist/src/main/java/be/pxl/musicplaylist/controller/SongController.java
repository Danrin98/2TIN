package be.pxl.musicplaylist.controller;

import be.pxl.musicplaylist.api.Genre;
import be.pxl.musicplaylist.api.Song;
import be.pxl.musicplaylist.service.SongService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist/songs")
public class SongController {

    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    SongService songService;
    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    // @Autowired
    // SongService songService;
    // => Dit is de 'oude' manier, nieuwe manier is contructor injection!

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody @Valid Song song) {
        //logger.info("Creating song: " + song);
        // => geeft fout (maar werkt ook) want de string is niet constant
        logger.info("Created song: {} as a new song with name: {}", song, song.getTitle());

        Song createdSong = songService.createSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getSongs();
    }

    @GetMapping("/{genre}")
    public List<Song> getSongByGenre(@PathVariable Genre genre) {
        return songService.getSongsByGenre(genre);
    }

    @PutMapping("/{index}")
    public Song updateSong(@PathVariable int index, @RequestBody Song song) {
            return songService.updateSong(index, song);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deleteSong(@PathVariable int index) {
        songService.deleteSong(index);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
