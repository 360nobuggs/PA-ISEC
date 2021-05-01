package logica;

public class Alien_preto extends Alien{
public Alien_preto(){
    super();
    }
    @Override
    public boolean attack() {
        if (dado.roll() == 1) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean death()
    {
        int aux=dado.roll();
        if (aux == 5||aux == 6) {
            return true;
        } else {
            return false;
        }
    }
    }

