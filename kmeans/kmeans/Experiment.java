package kmeans;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


/*
    Tsiouri Angeliki 3354
    Antoniou Christodoulos 2641

    This class executes multiple experiments using the K-Means algorithm with
    different numbers of clusters (M) (3,5,7,9,11 and 13).
    It saves the minimun clustering error for each number of clusters and 
    produces results in the form a plot with (min) clustering error per number of clusters.

    compile command:
    (navigate to kmeans)
    javac kmeans/Experiment.java

    run command:
    (navigate to kmeans)
    java kmeans/Experiment <reps>

    <reps>: number of times the K-Means algorithm is executed for each number of clusters.

    run example for 20 reps:
    (navigate to kmeans)
    java kmeans/Experiment 20

    outputs:
    "plots/clusters_3.png": plot for the execution of 3 clusters with minimum clustering error.
    "plots/clusters_5.png": plot for the execution of 5 clusters with minimum clustering error.
    "plots/clusters_7.png": plot for the execution of 7 clusters with minimum clustering error.
    "plots/clusters_9.png": plot for the execution of 9 clusters with minimum clustering error.
    "plots/clusters_11.png": plot for the execution of 11 clusters with minimum clustering error.
    "plots/clusters_13.png": plot for the execution of 13 clusters with minimum clustering error.
    "plots/results.png": plot with the (min) clustering error per number of clusters.
    "data/experiment_results.dat": data file with the (min) clustering error per number of clusters.
    "scripts/results.p": gnuplot script which generates the results plot.

    notes:
    >  The class loads the example data from the "data/examples.dat" file.
    >  The class also uses the following files to plot the executions of different num. of clusters:
        "scripts/plot.p"
        "data/cluster_*_members.dat" where * is the number of the cluster
        "data/cluster_centers.dat"
        These files are rewritten multiple times during the experiments execution.
        Therefore they are not particularly useful as they will only contain the 
        information of the last execution.
*/


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
