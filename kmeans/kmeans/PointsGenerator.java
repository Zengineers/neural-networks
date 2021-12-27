package kmeans;

import java.io.IOException;
import java.util.ArrayList;


/*
    Tsiouri Angeliki 3354
    Antoniou Christodoulos 2641

    This class is responsible for generating random points in 2D space.
    It exports the created points in the form of a plot and .dat file.

    compile command:
    (navigate to kmeans)
    javac kmeans/PointsGenerator.java

    run command:
    (navigate to kmeans)
    java kmeans/PointsGenerator

    outputs:
    "data/examples.dat": data file with randomly generated example points.
    "plots/examples.png": plot with the randomly generated example points.
*/


public class PointsGenerator {

    private static double generateRandomValue(double min, double max) {
        return (min + Math.random() * (max - min));
    }

    private static void addRandomPoints(ArrayList<Point> points, int count, 
                        double minX, double maxX, double minY, double maxY) {
        for (int i=0; i<count; i++) {
            double randomX = generateRandomValue(minX, maxX);
            double randomY = generateRandomValue(minY, maxY);

            points.add(new Point(randomX, randomY));
        }
    }

    private static void createRandomPoints(ArrayList<Point> points) {
        addRandomPoints(points, 150, 0.75, 1.25, 0.75, 1.25);
        addRandomPoints(points, 150, 0.0, 0.5, 0.0, 0.5);
        addRandomPoints(points, 150, 0.0, 0.5, 1.5, 2.0);
        addRandomPoints(points, 150, 1.5, 2.0, 0.0, 0.5);
        addRandomPoints(points, 150, 1.5, 2.0, 1.5, 2.0);
        addRandomPoints(points, 75, 0.6, 0.8, 0.0, 0.4);
        addRandomPoints(points, 75, 0.6, 0.8, 1.6, 2.0);
        addRandomPoints(points, 75, 1.2, 1.4, 0.0, 0.4);
        addRandomPoints(points, 75, 1.2, 1.4, 1.6, 2.0);
        addRandomPoints(points, 150, 0.0, 2.0, 0.0, 2.0);
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Point> points = new ArrayList<Point>();
        createRandomPoints(points);
        Plotter.plotExamples(points);
    }
}