package modelers;

import java.util.Random;
import java.util.ArrayList;
import java.util.TreeMap;

public class SongStructureMarkov
{
    private int order;
    private final TreeMap<String, ArrayList<String>> songStructureMap;
    private String[] songStructures;
    
    public SongStructureMarkov(int order) {
        this.order = order;
        this.songStructureMap = new TreeMap<>();
    }
    
    public void setTraining(String[] songStructures) {
        this.songStructures = songStructures;
        for (String structure : songStructures) {
            for (int i = 0; i < structure.length() - this.order; ++i) {
                String verseSection = structure.substring(i, this.order + i);
                if (this.songStructureMap.containsKey(verseSection)) {
                    this.songStructureMap.get(verseSection).add(structure.substring(this.order + i, this.order + i + 1));
                }
                else {
                    ArrayList<String> nextValues = new ArrayList<>();
                    nextValues.add(structure.substring(this.order + i, this.order + i + 1));
                    this.songStructureMap.put(verseSection, nextValues);
                }
                if (i + this.order > structure.length()) {
                    this.songStructureMap.get(verseSection).add("");
                }
            }
        }
    }
    
    public String getRandomSongStructure() {
        Random random = new Random();
        StringBuilder songStructureBuilder = new StringBuilder();
        int maxNumVerses = 0;
        int minNumVerses = Integer.MAX_VALUE;
        for (String structure : this.songStructures) {
            if (structure.length() > maxNumVerses) {
                maxNumVerses = structure.length();
            }
            if (structure.length() < minNumVerses) {
                minNumVerses = structure.length();
            }
        }
        int numVerses = random.nextInt(maxNumVerses - minNumVerses) + minNumVerses;
        int arrayIndex = random.nextInt(this.songStructures.length);
        String current = this.songStructures[arrayIndex].substring(0, this.order);
        songStructureBuilder.append(current);
        for (int i = 0; i < numVerses; ++i) {
            ArrayList<String> nextValues = this.songStructureMap.get(current);
            if (nextValues == null || nextValues.isEmpty()) {
                nextValues = new ArrayList<>();
            }
            arrayIndex = nextValues.isEmpty() ? 0 : random.nextInt(nextValues.size());
            String nextItem = nextValues.isEmpty() ? "" : nextValues.get(arrayIndex);
            if (nextItem == null || nextItem.isEmpty()) {
                break;
            }
            songStructureBuilder.append(nextItem);
            current = current.substring(1) + nextItem;
        }
        return songStructureBuilder.toString();
    }
}