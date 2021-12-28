# Neural Networks

This repo features projects revolving around neural networks developed based on the Computational Intelligence course [@cse.uoi.gr](https://www.cs.uoi.gr/).
<br><br>

### PROJECT 1: K-Means clustering 
<br>

This project implements a version of the [K-Means clustering](https://en.wikipedia.org/wiki/K-means_clustering) algorithm which partitions 2-dimensional points into M clusters. The algorithm is executed multiple times for different numbers of clusters. The minimum clustering error(*) is kept for each number of clusters and a respective plot is generated. Finally, a plot which shows the variation of the (minimum) clustering error per number of clusters is generated.

The project is split in three parts:
* Random Points Generation
* K-Means algorithm implementation
* Experiment with multiple executions of the algorithm

Each part is executed and compiled separately. For ease of use, a shell script (compile.sh) is included which compiles all 3 parts. To execute the script (or any other program of the project) change to dir kmeans (root directory of the project). Moreover, execution rights might be required for the script. This can be done with the following command:
<br>
chmod +x compile,.sh

Developed with java 14.0.2.

(*) Clustering error: Sum of the euclidean distances of the example points from the center of the cluster they belongs to, after the algorithm has finished.
<br><br>

### Contents
<br>

The project contains the following directories:
1. kmeans (kmeans/kmeans) - kmeans package with the source code.
2. data (kmeans/data) - directory with .dat files which contain the generated data.
3. plots (kmeans/plots) - directory with the generated plots.
4. scripts (kmeans/scripts) - gnuplot scripts which are generated and executed by the programs.
<br>

The source code has the following classes:
1. Point: This class describes a 2-dimensional point.
2. Cluster: This class describes a cluster of the K-Means algorithm.
3. Loader: This class is responsible for loading data from a file. It perfoms no error-checking and assumes the data in the file are in proper format.
4. Plotter:  This class is responsible for plotting. It writes data to .dat files, writes gnuplot scripts and runs them to plot graphs for the example points, the resulting clusters and the experiment results.
5. PointsGenerator: This class is responsible for generating random points in 2D space. It exports the created points in the form of a plot and .dat file.
6. KMeans: This class implements the K-Means algorithm. It exports the results in the form of a plot and .dat file.
7. Experiment: This class executes multiple experiments using the K-Means algorithm with different numbers of clusters (M) (3,5,7,9,11 and 13). It saves the minimun clustering error for each number of clusters and produces results in the form a plot with (min) clustering error per number of clusters.
<br><br>

### Usage
<br>

* Random Points Generation
    
    compile command: <br>
    (navigate to kmeans) <br>
    javac kmeans/PointsGenerator.java <br><br>

    run command: <br>
    (navigate to kmeans) <br>
    java kmeans/PointsGenerator <br><br>

    outputs: <br>
    "data/examples.dat": data file with randomly generated example points. <br>
    "plots/examples.png": plot with the randomly generated example points. <br>
    "scripts.p/examples.p": gnuplot script which generates the plot of the example points. <br><br>

* K-Means algorithm implementation

    compile command: <br>
    (navigate to kmeans) <br>
    javac kmeans/KMeans.java <br><br>

    run command: <br>
    (navigate to kmeans) <br>
    java kmeans/KMeans \<number of clusters> <br><br>

    \<number of clusters>: number of clusters the algorithm should create. <br><br>

    run example for 9 clusters: <br>
    (navigate to kmeans) <br>
    java kmeans/KMeans 9 <br><br>

    outputs: <br>
    "plots/clusters.png": plot with the resulting clusters of the algorithm.
        The plot showcases the center of each cluster and the members of each cluster in different color. <br>
    "data/cluster_*_members.dat" where * is the number of the cluster: data files containing the members of each cluster. <br>
    "data/cluster_centers.dat": data file containing the centers of the different clusters. <br>
    "scripts/plot.p": gnuplot script which generates the clusters plot. <br><br>


* Experiment with multiple executions of the algorithm

    compile command: <br>
    (navigate to kmeans) <br>
    javac kmeans/Experiment.java <br><br>

    run command: <br>
    (navigate to kmeans) <br>
    java kmeans/Experiment \<reps> <br><br>

    \<reps>: number of times the K-Means algorithm is executed for each number of clusters. <br><br>

    run example for 20 reps: <br>
    (navigate to kmeans) <br>
    java kmeans/Experiment 20 <br><br>

    outputs: <br>
    "plots/clusters_3.png": plot for the execution of 3 clusters with minimum clustering error. <br>
    "plots/clusters_5.png": plot for the execution of 5 clusters with minimum clustering error. <br>
    "plots/clusters_7.png": plot for the execution of 7 clusters with minimum clustering error. <br>
    "plots/clusters_9.png": plot for the execution of 9 clusters with minimum clustering error. <br>
    "plots/clusters_11.png": plot for the execution of 11 clusters with minimum clustering error. <br>
    "plots/clusters_13.png": plot for the execution of 13 clusters with minimum clustering error. <br>
    "plots/results.png": plot with the (min) clustering error per number of clusters.
    "data/experiment_results.dat": data file with the (min) clustering error per number of clusters. <br>
    "scripts/results.p": gnuplot script which generates the results plot. <br><br>

    notes: <br>
    The Experiment class loads the example data from the "data/examples.dat" file. <br>
    The Experiment class also uses the following files to plot the executions of different num. of clusters: <br>
        "scripts/plot.p" <br>
        "data/cluster_*_members.dat" where * is the number of the cluster <br>
        "data/cluster_centers.dat" <br>
        These files are rewritten multiple times during the experiments execution. <br>
        Therefore they are not particularly useful as they will only contain the 
        information of the last execution. <br>