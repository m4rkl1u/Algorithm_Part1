package org.coursera.algorithm.part1;

import java.util.Random;

public class PercolationStats {

    private Random ri = new Random(); // randomizer for pick up i and j
    //private Random rj = new Random(ri.nextInt());
    private double[] count; // record the percentage of iteration

    /**
     * perform T independent computational experiments on an N-by-N grid
     * 
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("illegal parameter");
        }        
        count = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);

            count[i] = 0;
            while (true) {
                int j = ri.nextInt(N) + 1;
                int k = ri.nextInt(N) + 1;
                if(!p.isOpen(j, k)){
                    p.open(j, k);
                    count[i] += 1;
                }
                if(p.percolates()) {
                    break;
                }
            }
            count[i] = count[i] / (N * N);
        }
    }

    /**
     * sample mean of percolation threshold
     * 
     * @return average
     */
    public double mean() {
        double d = 0.;
        for (int i = 0; i < count.length; i++) {
            d += count[i];
        }

        return d / count.length;
    }

    /**
     * sample standard deviation of percolation threshold
     * 
     * @return the stddev
     */
    public double stddev() {
        if (count.length == 1){
            return Double.NaN;
        }
        double avg = mean();
        double d = 0.0;
        for (int i = 0; i < count.length; i++) {
            d += (count[i] - avg) * (count[i] - avg);
        }
        return Math.sqrt(d / (count.length - 1));
    }

    /**
     * returns lower bound of the 95% confidence interval
     * 
     * @return lower bound
     */
    public double confidenceLo() {
        if(count.length == 0) return 0; 
        return mean() - (1.96 * stddev() / Math.sqrt(count.length));
    }

    /**
     * returns upper bound of the 95% confidence interval
     * 
     * @return the upper bound
     */
    public double confidenceHi() {
        if(count.length == 0) return 1; 
        return mean() + (1.96 * stddev() / Math.sqrt(count.length));
    }

    /**
     * test client, described below
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stat = new PercolationStats(N, T);
        System.out.println("mean                    = " + stat.mean());
        System.out.println("stddev                  = " + stat.stddev());
        System.out.println("95% confidence interval = " + stat.confidenceLo()
                + ", " + stat.confidenceHi());

    }
}