package kmeans;

import java.io.IOException;
import java.util.ArrayList;

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