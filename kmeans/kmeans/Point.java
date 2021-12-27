package kmeans;

import java.util.ArrayList;


/*
    Tsiouri Angeliki 3354
    Antoniou Christodoulos 2641

    This class describes a 2-dimensional point.
*/


public class Point {
    
    private double x;
    private double y;
    private ArrayList<Double> distances;

    public Point(double x, double y) {
        distances = new ArrayList<Double>();
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public boolean equals(Point point) {
        return (this.x == point.x && this.y == point.y);
    }

    public ArrayList<Double> getDistances() {
        return distances;
    }

    public void addDistance(double distance) {
        distances.add(distance);
    }

    public void clearDistances() {
        distances.clear();
    }

}
