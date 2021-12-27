package kmeans;

import java.util.ArrayList;

public class Cluster {
    
    private Point center;
    private Point previousCenter;
    private ArrayList<Point> members;

    public Cluster(Point center) {
        members = new ArrayList<Point>();
        this.center = center;
    }

    public ArrayList<Point> getMembers() {
        return members;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Point getPreviousCenter() {
        return previousCenter;
    }

    public void setPreviousCenter(Point previousCenter) {
        this.previousCenter = previousCenter;
    }

    public void savePreviousCenter() {
        previousCenter = new Point(center.getX(), center.getY());
    }

    public void updateCenter() {
        double sumX = 0;
        double sumY = 0;
        double meanX;
        double meanY;
        for (Point member : members) {
            sumX += member.getX();
            sumY += member.getY();
        }

        if (members.size() == 0) {
            meanX = sumX;
            meanY = sumY;
        }
        else {
            meanX = (double) sumX / (double) members.size();
            meanY = (double) sumY / (double) members.size();
        }
        center = new Point(meanX, meanY);
    }

    public void addMember(Point member) {
        members.add(member);
    }

    public void clearMembers() {
        members.clear();
    }

    public double calculateClusteringError() {
        double clusteringError = 0;
        for (Point member : members) {
            clusteringError += 
                Math.pow(member.getX() - center.getX(), 2) +
                Math.pow(member.getY() - center.getY(), 2);
        }
        return clusteringError;
    }
}
