package kmeans;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


/*
    Tsiouri Angeliki 3354
    Antoniou Christodoulos 2641

    This class implements the K-Means algorithm.
    It exports the results in the form of a plot and .dat file.

    compile command:
    (navigate to kmeans)
    javac kmeans/KMeans.java

    run command:
    (navigate to kmeans)
    java kmeans/KMeans <number of clusters>

    <number of clusters>: number of clusters the algorithm should create.

    run example for 9 clusters:
    (navigate to kmeans)
    java kmeans/KMeans 9

    outputs:
    "plots/clusters.png": plot with the resulting clusters of the algorithm. 
        The plot showcases the center of each cluster and the members of each cluster in different color.
    "data/cluster_*_members.dat" where * is the number of the cluster: data files containing the 
        members of each cluster.
    "data/cluster_centers.dat": data file containing the centers of the different clusters.
    "scripts/plot.p": gnuplot script which generates the clusters plot.
*/


public class KMeans {
    
    private ArrayList<Point> points;
    private ArrayList<Cluster> clusters;
    private int centersCounter;  // M
    private int epochCounter;    // t
    private boolean centersChanged;
    private double clusteringError;
    private String plotTitle;


    public KMeans(String examplesFile, int centersCounter) {
        points = Loader.loadFile(examplesFile);
        this.centersCounter = centersCounter;
    }

    public double getClusteringError() {
        return clusteringError;
    }

    public void runKMeans() {
        initialize();
        while (centersChanged) {
            System.out.print("\rRunning for epoch: " + String.valueOf(epochCounter+1));
            clearClustersMembers();
            iteratePoints();
            updateCenters();
            checkTermination();
        }
    }

    private void initialize() {
        epochCounter = 0;
        centersChanged = true;
        createClusters();
    }

    private void createClusters() {
        clusters = new ArrayList<Cluster>();
        for (int i=0; i<centersCounter; i++) {
            int randInt = generateRandomInt(0, points.size());
            clusters.add(new Cluster(points.get(randInt)));
        }
    }

    private void clearClustersMembers() {
        for (Cluster cluster : clusters) {
            cluster.clearMembers();
        }
    }

    private void iteratePoints() {
        for (Point point : points) {
            point.clearDistances();
            calculateDistances(point);
            placePointInCluster(point);
        }
    }

    private void calculateDistances(Point point) {
        for (Cluster cluster : clusters) {
            Point center = cluster.getCenter();
            double distance = 
                Math.pow(point.getX() - center.getX(), 2) +
                Math.pow(point.getY() - center.getY(), 2);
            point.addDistance(distance);
        }
    }

    private void updateCenters() {
        for (Cluster cluster : clusters) {
            cluster.savePreviousCenter();
            cluster.updateCenter();
        }
    }

    private void checkTermination() {
        centersChanged = false;
        for (Cluster cluster : clusters) {
            Point previousCenter = cluster.getPreviousCenter();
            Point center = cluster.getCenter();
            if (!previousCenter.equals(center)) {
                centersChanged = true;
                break;
            }
        }
        epochCounter++;
    }

    private void placePointInCluster(Point point) {
        Double min = Collections.min(point.getDistances());
        int indexOfMin = point.getDistances().indexOf(min);
        Cluster cluster = clusters.get(indexOfMin);
        cluster.addMember(point);
    }

    private int generateRandomInt(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }

    private void calculateClusteringError() {
        clusteringError = 0;
        for (Cluster cluster : clusters) {
            clusteringError += cluster.calculateClusteringError();
        }
        System.out.println("\rClustering Error: " + new DecimalFormat("#.###").format(clusteringError));
    }
    
    private void setPlotTile() {
        plotTitle = "Clusters: " + centersCounter + "  " +
            "Clustering error: " + new DecimalFormat("#.###").format(clusteringError);
    }
    
    public void execute() {
        runKMeans();
        calculateClusteringError();
        setPlotTile();            
    }

    public void plot(String scriptFile, String outputFile) 
        throws IOException, InterruptedException {
            Plotter.plotClusters(clusters, scriptFile, plotTitle, outputFile);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        KMeans kMeans = new KMeans("data/examples.dat", Integer.parseInt(args[0]));
        kMeans.execute();
        kMeans.plot("scripts/plot.p", "plots/clusters.png");
    }
    
}
