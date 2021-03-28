package utils;

import java.io.IOException;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import modelers.LyricsModeler;

public class SongGeneratorUtils
{
    public String generateFullSong(String pathToFolder, int songOrder, int songStructureOrder, FileParser fileParser) throws IOException {
        LyricsModeler lyricsModeler = new LyricsModeler(songOrder);
        List<Path> pathToSongs = Files.walk(Paths.get(pathToFolder)).filter(Files::isRegularFile).collect(Collectors.toList());
        String[] allSongLyrics = new String[pathToSongs.size()];
        int index = 0;
        for (Path individualSongPath : pathToSongs) {
            String lyrics = fileParser.parseFileToString(individualSongPath.toString());
            allSongLyrics[index++] = lyrics;
        }
        lyricsModeler.setTraining(allSongLyrics);
        return lyricsModeler.generateSong(songStructureOrder);
    }
}