package kmeans;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class KMeans {
    
    private static ArrayList<Point> points;
    private static ArrayList<Cluster> clusters;
    private static int centersCounter;  // M
    private static int epochCounter;    // t
    private static boolean centersChanged;


    public static void runKMeans() {
        initialize();
        while (centersChanged) {
            System.out.print("\rRunning for epoch: " + String.valueOf(epochCounter+1) + " ...");
            clearClustersMembers();
            iteratePoints();
            updateCenters();
            checkTermination();
        }

    }

    private static void initialize() {
        epochCounter = 0;
        centersChanged = true;
        createClusters();
    }

    private static void createClusters() {
        clusters = new ArrayList<Cluster>();
        for (int i=0; i<centersCounter; i++) {
            int randInt = generateRandomInt(0, points.size());
            clusters.add(new Cluster(points.get(randInt)));
        }
    }

    private static void clearClustersMembers() {
        for (Cluster cluster : clusters) {
            cluster.clearMembers();
        }
    }

    private static void iteratePoints() {
        for (Point point : points) {
            point.clearDistances();
            calculateDistances(point);
            placePointInCluster(point);
        }
    }

    private static void calculateDistances(Point point) {
        for (Cluster cluster : clusters) {
            Point center = cluster.getCenter();
            double distance = 
                Math.pow(point.getX() - center.getX(), 2) +
                Math.pow(point.getY() - center.getY(), 2);
            point.addDistance(distance);
        }
    }
    

    private static void updateCenters() {
        for (Cluster cluster : clusters) {
            cluster.savePreviousCenter();
            cluster.updateCenter();
        }
    }

    private static void checkTermination() {
        // System.out.println("\n============= CHECK =============");
        centersChanged = false;
        for (Cluster cluster : clusters) {
            Point previousCenter = cluster.getPreviousCenter();
            Point center = cluster.getCenter();
            if (!previousCenter.equals(center)) {
                centersChanged = true;
                // System.out.println("Previous center: " + previousCenter.getX() +" "+ previousCenter.getY());
                // System.out.println("Center: " + center.getX() +" "+ center.getY());
                // System.out.println("Centers changed? " + centersChanged);
                break;
            }

        }
        // System.out.println("================================\n");
        // System.out.println("FINAL Centers changed? " + centersChanged);

        epochCounter++;
    }

    private static void placePointInCluster(Point point) {
        Double min = Collections.min(point.getDistances());
        int indexOfMin = point.getDistances().indexOf(min);
        Cluster cluster = clusters.get(indexOfMin);
        cluster.addMember(point);
    }

    private static int generateRandomInt(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }

    private static double calculateClusteringError() {
        double clusteringError = 0;
        for (Cluster cluster : clusters) {
            clusteringError += cluster.calculateClusteringError();
        }
        return clusteringError;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        points = Loader.loadFile("examples");
        centersCounter = 5;
        runKMeans();

        // Plotter.exportClustersToFile(clusters);
        // Plotter.generateGnuplotScript(clusters.size());
        // Plotter.runScript("plot.p");
        double clusteringError = calculateClusteringError();
        String plotTitle = "Clusters: " + centersCounter + "  " +
            "Clustering error: " + new DecimalFormat("#.###").format(clusteringError);
        Plotter.plot(clusters, "plot.p", plotTitle);

        // int total = 0;
        // for (Cluster cluster : clusters) {
        //     total += cluster.getMembers().size();
        // }
        // System.out.println("\ntotal examples: " + total);
        System.out.println("\nClustering error: " + clusteringError);


    }
    
}
