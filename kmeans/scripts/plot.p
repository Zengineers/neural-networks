set terminal png size 900,600
set output "plots/clusters_13.png
set title "Clusters: 13  Clustering error: 39.201"
set key outside top

plot "data/cluster_centers.dat" with points pointtype 16 linecolor -1, \
	"data/cluster_1_members.dat" with points pointtype 1 linecolor 1, \
	"data/cluster_2_members.dat" with points pointtype 1 linecolor 2, \
	"data/cluster_3_members.dat" with points pointtype 1 linecolor 3, \
	"data/cluster_4_members.dat" with points pointtype 1 linecolor 4, \
	"data/cluster_5_members.dat" with points pointtype 1 linecolor 5, \
	"data/cluster_6_members.dat" with points pointtype 1 linecolor 6, \
	"data/cluster_7_members.dat" with points pointtype 1 linecolor 7, \
	"data/cluster_8_members.dat" with points pointtype 1 linecolor 8, \
	"data/cluster_9_members.dat" with points pointtype 1 linecolor 9, \
	"data/cluster_10_members.dat" with points pointtype 1 linecolor 10, \
	"data/cluster_11_members.dat" with points pointtype 1 linecolor 11, \
	"data/cluster_12_members.dat" with points pointtype 1 linecolor 12, \
	"data/cluster_13_members.dat" with points pointtype 1 linecolor 13, \
