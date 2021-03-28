package utils;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FileParser
{
    public String parseFileToString(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.next());
            sb.append(" ");
        }
        return sb.toString();
    }
}
