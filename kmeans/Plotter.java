package kmeans;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Plotter {
    
    public static void writeClusterMembersToFile(Cluster cluster, int index) throws IOException {
        String members = "#\tx\ty\n";
        for (Point member : cluster.getMembers()) {
            members += String.valueOf(member.getX()) + " " + String.valueOf(member.getY()) + "\n";
        }

        FileWriter writer = new FileWriter("cluster_" + String.valueOf(index) + "_members");
        writer.write(members);
        writer.close();
    }

    public static void exportClustersToFile(ArrayList<Cluster> clusters) throws IOException {
        String centers = "#\tx\ty\n";
        for (Cluster cluster : clusters) {
            writeClusterMembersToFile(cluster, clusters.indexOf(cluster)+1);
            Point center = cluster.getCenter();
            centers += String.valueOf(center.getX()) + " " + String.valueOf(center.getY()) + "\n";
        }
        
        FileWriter writer = new FileWriter("cluster_centers");
        writer.write(centers);
        writer.close();
    }

    public static void generateGnuplotScript(int clustersCounter, String title) throws IOException {
        String contents = "set terminal png size 800,600\n" +
            "set output \"clusters.png\"\n" +
            "set title \"" + title + "\"\n" +
            "set key outside top\n\n" +
            "plot \"cluster_centers\" with points pointtype 16 linecolor -1, \\\n";

        for (int i=0; i<clustersCounter; i++) {
            contents += "\t\"cluster_" + String.valueOf(i+1) +
                "_members\" with points pointtype 1 linecolor " +
                String.valueOf(i+1) + ", \\\n";
        }

        FileWriter writer = new FileWriter("plot.p");
        writer.write(contents);
        writer.close();
    }

    public static void runScript(String scriptName) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("gnuplot " + scriptName);
        process.waitFor();
    }

    public static void plot(ArrayList<Cluster> clusters, String scriptName, String title) 
        throws IOException, InterruptedException {
            exportClustersToFile(clusters);
            generateGnuplotScript(clusters.size(), title);
            runScript(scriptName);
    }
}
