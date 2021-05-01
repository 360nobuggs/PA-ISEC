package states;
import logica.*;

import java.util.Scanner;

public abstract class StateAdapter implements IStates
{

    protected DataGame dataGame;
    public static final Scanner scan = new Scanner(System.in);
    public StateAdapter(DataGame dataGame)
    {
        this.dataGame = dataGame;
    }

    public DataGame getDataGame()
    {
        return dataGame;
    }
    public void setDataGame(DataGame dataGame)
    {
        this.dataGame = dataGame;
    }

    @Override
    public IStates space_station(){ return this; }
    @Override
    public IStates space_ex(){ return this; }
    @Override
    public IStates planet_ex(){ return this; }
    @Override
    public IStates ship(int s){return  this;};
    @Override
    public  IStates win_loss_check(){return this;}
    @Override
    public IStates winner(){ return this; }
    @Override
    public IStates game_over(){ return this; }
    @Override
    public IStates novo_jogo(){return this;}
    @Override
    public IStates convert_space(int escolha){return this;}
    @Override
    public IStates upgrade_cargo(){return this;}
    @Override
    public IStates refill() {return  this; }
    @Override
    public IStates buy_drone(){return  this; }
    @Override
    public IStates buy_trip(){return  this; }
    @Override
    public IStates conversao(int velho, int novo, int numero){return  this; }
    @Override
    public IStates movimento(char direcao){return this;}
    @Override
    public IStates add_res(){ return this;}
    @Override
    public IStates explorar_novamente(){return this;}



}