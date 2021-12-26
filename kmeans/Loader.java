package kmeans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
    
    public static ArrayList<Point> loadFile(String filename) {
        String contents = readContents(filename);
        return convertContentsToList(contents);
    }

    private static String readContents(String filename) {
		String contents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNextLine()) {
				contents = contents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contents;
	}

    private static ArrayList<Point> convertContentsToList(String contents) {
        ArrayList<Point> points = new ArrayList<Point>();
        String[] lines = splitContentsToLines(contents);
        for (String line : lines) {
            String[] words = splitLineToWords(line);

            double X = Double.parseDouble(words[0]);
            double Y = Double.parseDouble(words[1]);
            points.add(new Point(X, Y));
        }
        return points;
    }

    private static String[] splitContentsToLines(String contents) {
		return contents.split("\\r?\\n");
	}
	
	private static String[] splitLineToWords(String line) {
		return line.split("\\s+");
	}
}
