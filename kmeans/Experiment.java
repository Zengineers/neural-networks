package kmeans;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Experiment {
    
    private static ArrayList<KMeans> executions = new ArrayList<KMeans>();
    private static ArrayList<Double> clusteringErrors = new ArrayList<Double>();
    private static String experimentResults = "";

    public static void runExperiment(int times, int centersCounter) 
        throws IOException, InterruptedException {
            System.out.println("\n============= Running Experiment =============");
            for (int i=0; i<times; i++) {
                KMeans kMeans = new KMeans("data/examples.dat", centersCounter);
                kMeans.execute();
                executions.add(kMeans);
                clusteringErrors.add(kMeans.getClusteringError());
            }

            double minClusteringError = plotExecutionWithMinClusteringError(centersCounter);
            clear();

            printResults(times, centersCounter, minClusteringError);
            experimentResults += 
                String.valueOf(centersCounter) + " " + 
                String.valueOf(minClusteringError) + "\n";
    }

    private static void plotResults() throws IOException, InterruptedException {
        exportResultsToFile();
        Plotter.plotExperimentResults();
    }

    private static void exportResultsToFile() throws IOException {
        FileWriter writer = new FileWriter("data/experiment_results.dat");
        writer.write(experimentResults);
        writer.close();
    }

    private static void printResults(int times, int centersCounter, double minClusteringError) {
        String message = "\n> Results " +
            "\n\tReps: " + times + 
            "\n\tClusters: " + centersCounter +
            "\n\tMin Clustering Error: " + new DecimalFormat("#.###").format(minClusteringError) +
            "\n==============================================\n";
        System.out.println(message);
    }

    private static double plotExecutionWithMinClusteringError(int centersCounter) 
        throws IOException, InterruptedException {
            Double min = Collections.min(clusteringErrors);
            int indexOfMin = clusteringErrors.indexOf(min);

            // System.out.println("min: " + new DecimalFormat("#.###").format(min) + "\n");
            String outputFile = "plots/clusters_" + centersCounter + ".png";
            executions.get(indexOfMin).plot("scripts/plot.p", outputFile);

            return min;
    }

    private static void clear() {
        executions.clear();
        clusteringErrors.clear();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int reps = Integer.parseInt(args[0]);
        runExperiment(reps, 3);
        runExperiment(reps, 5);
        runExperiment(reps, 7);
        runExperiment(reps, 9);
        runExperiment(reps, 11);
        runExperiment(reps, 13);

        plotResults();
    }
}
