set terminal png size 800,600
set output "clusters.png"
set title "Clusters: 5  Clustering error: 105.752"
set key outside top

plot "cluster_centers" with points pointtype 16 linecolor -1, \
	"cluster_1_members" with points pointtype 1 linecolor 1, \
	"cluster_2_members" with points pointtype 1 linecolor 2, \
	"cluster_3_members" with points pointtype 1 linecolor 3, \
	"cluster_4_members" with points pointtype 1 linecolor 4, \
	"cluster_5_members" with points pointtype 1 linecolor 5, \
