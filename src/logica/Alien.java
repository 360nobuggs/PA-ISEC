package logica;

import java.io.Serializable;

public class Alien implements Serializable {
  protected   D10 dado = new D10(6);
public Alien()
{

}
public boolean attack()
{
    return false;
}//retorna true se a probabilidade de acertar corresconde ao tipo de alien
public boolean death()
{
    return false;
}
}
