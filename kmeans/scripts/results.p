set terminal png size 900,600
set output "plots/results.png
set title "Results"
set xlabel "Clusters"
set ylabel "Clustering Error"
set key outside top
plot "data/experiment_results.dat" with linespoints pointtype 5 linecolor -1