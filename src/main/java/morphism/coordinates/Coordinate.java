package morphism.coordinates;

/**
 * A representation of a point in a particular
 * coordinate system. A coordinate is simply a
 * sequence of coefficients.
 */
public class Coordinate {

    final private double[] coefficients;
    final private int coefficientsSize;

    public Coordinate(int nVertices) {
        coefficients = new double[nVertices];
        coefficientsSize = nVertices;

        for (int i = 0; i < nVertices; i++)
            coefficients[i] = 0.0;
    }


    public int size() {
        return coefficientsSize;
    }

    /**
     * Get a specific coordinate, assuming that coefficients are real numbers.
     *
     * @param i The coordinate index
     * @return The coordinate at index i
     */
    public double getCoefficient(int i) {
        return coefficients[i];
    }

    public void setCoefficient(int i, double value) {
        coefficients[i] = value;
    }

    @Override
    public String toString() {

        String coefficientsString = "";

        for (int i=0; i < coefficients.length; i++)
            coefficientsString +=  (coefficients[i]) + " ";

        return "Coordinate{" +
                "coefficients= {" + coefficientsString + "} " +
                ", coefficientsSize=" + coefficientsSize +
                '}';
    }
}
