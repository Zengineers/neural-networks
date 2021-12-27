package kmeans;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Plotter {
    
    private static void exportExamplesToFile(ArrayList<Point> points) throws IOException {
        String examples = "";
        for (Point point : points) {
            examples += String.valueOf(point.getX()) + " " + String.valueOf(point.getY()) + "\n";
        }

        FileWriter writer = new FileWriter("data/examples.dat");
        writer.write(examples);
        writer.close();
    }

    private static void exportClusterMembersToFile(Cluster cluster, int index) throws IOException {
        String members = "#\tx\ty\n";
        for (Point member : cluster.getMembers()) {
            members += String.valueOf(member.getX()) + " " + String.valueOf(member.getY()) + "\n";
        }

        FileWriter writer = new FileWriter("data/cluster_" + String.valueOf(index) + "_members.dat");
        writer.write(members);
        writer.close();
    }

    private static void exportClustersToFile(ArrayList<Cluster> clusters) throws IOException {
        String centers = "#\tx\ty\n";
        for (Cluster cluster : clusters) {
            exportClusterMembersToFile(cluster, clusters.indexOf(cluster)+1);
            Point center = cluster.getCenter();
            centers += String.valueOf(center.getX()) + " " + String.valueOf(center.getY()) + "\n";
        }   

        FileWriter writer = new FileWriter("data/cluster_centers.dat");
        writer.write(centers);
        writer.close();
    }

    private static void generateGnuplotScript(
        int clustersCounter,
        String title,
        String scriptFile,
        String outputFile) 
        throws IOException {
            String contents = "set terminal png size 900,600\n" +
                "set output \"" + outputFile + "\n" +
                "set title \"" + title + "\"\n" +
                "set key outside top\n\n" +
                "plot \"data/cluster_centers.dat\" with points pointtype 16 linecolor -1, \\\n";

            for (int i=0; i<clustersCounter; i++) {
                contents += "\t\"data/cluster_" + String.valueOf(i+1) +
                    "_members.dat\" with points pointtype 1 linecolor " +
                    String.valueOf(i+1) + ", \\\n";
            }

            FileWriter writer = new FileWriter(scriptFile);
            writer.write(contents);
            writer.close();
    }

    private static void generateGnuplotScript(
        String title, 
        String scriptFile, 
        String outputFile,
        String xlabel,
        String ylabel,
        String dataFile,
        String plotParams) 
        throws IOException {
            String contents = "set terminal png size 900,600\n" +
                "set output \"" + outputFile + "\n" +
                "set title \"" + title + "\"\n" +
                "set xlabel \"" + xlabel + "\"\n" +
                "set ylabel \"" + ylabel + "\"\n" +
                "set key outside top\n" + 
                "plot \"" + dataFile + "\" " + plotParams;

            FileWriter writer = new FileWriter(scriptFile);
            writer.write(contents);
            writer.close();
    }

    private static void runScript(String scriptFile) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("gnuplot " + scriptFile);
        process.waitFor();
    }

    public static void plotExamples(ArrayList<Point> points) 
        throws IOException, InterruptedException {
            exportExamplesToFile(points);
            generateGnuplotScript(
                "Examples", 
                "scripts/examples.p", 
                "plots/examples.png", 
                "x", 
                "y", 
                "data/examples.dat",
                "with points pointtype 1 linecolor 3");
            runScript("scripts/examples.p");
    }

    public static void plotClusters(ArrayList<Cluster> clusters, String scriptFile, String title, String outputFile) 
        throws IOException, InterruptedException {
            exportClustersToFile(clusters);
            generateGnuplotScript(clusters.size(), title, scriptFile, outputFile);
            runScript(scriptFile);
    }

    public static void plotExperimentResults() throws IOException, InterruptedException {
        generateGnuplotScript(
            "Results", 
            "scripts/results.p", 
            "plots/results.png", 
            "Clusters", 
            "Clustering Error", 
            "data/experiment_results.dat",
            "with linespoints pointtype 5 linecolor -1");
        runScript("scripts/results.p");
    }
}
