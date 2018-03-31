package implementations;


import interfaces.Algo;

public class Consumer {
    public static void main(String[] args) {
        Algo algo = new LinearRegressor();

        fitWithAlgo(algo);
    }


    private static void fitWithAlgo(Algo algo) {
        algo.fit();
    }

}
