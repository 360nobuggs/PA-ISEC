package logica;

public class Alien_azul extends Alien{
    public Alien_azul(){
        super();
    }
    @Override
    public boolean attack() {
        int aux=dado.roll();
        if (aux == 5||aux == 6||aux ==4) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean death()
    {
        int aux=dado.roll();
        if (aux == 3||aux == 4||aux==5) {
            return true;
        } else {
            return false;
        }
    }
}

