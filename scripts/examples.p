set terminal png size 800,600
set output "plots/examples.png
set title "Examples"
set xlabel "x"
set ylabel "y"
set key outside top
plot "data/examples.dat" with points pointtype 1 linecolor 3