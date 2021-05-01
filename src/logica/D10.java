package logica;
import java.io.Serializable;
import java.util.Random;
public class D10 implements Serializable {
    private Random numberGenerator;
    private  int max = 10;

    private final int min = 1;

    public D10(int u) {
        max= u;
        this.numberGenerator = new Random(System.currentTimeMillis());
    }

    public int roll() {

        return numberGenerator.nextInt((max - min) + 1) + min;
    }

}