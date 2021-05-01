package logica;

public class Alien_vermelho extends Alien{
    public Alien_vermelho(){
        super();
    }
    @Override
    public boolean attack() {
        int aux=dado.roll();
        if (aux == 5||aux == 6) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean death()
    {
        int aux=dado.roll();
        if (aux == 1||aux == 2) {
            return true;
        } else {
            return false;
        }
    }
}

