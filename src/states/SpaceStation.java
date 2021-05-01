package states;

import logica.DataGame;

public class SpaceStation extends StateAdapter{
    public SpaceStation(DataGame data)
    {
        super(data);
    }

    @Override
    public IStates space_ex()
    {
        getDataGame().mensagem_clear();
        getDataGame().logica_space_ex();
        getDataGame().generate_planet();
        getDataGame().datadisplay();
        return new SpaceEx(dataGame);
    }
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_SPACE_STATION;
    }
    @Override
    public IStates refill()
    {
        dataGame.refill();
        return  this;
    }
    @Override
    public IStates buy_drone()
    {
        dataGame.buy_drone();
        return  this;
    }
    @Override
    public IStates buy_trip()
    {
        dataGame.buy_tripulacao();
        return  this;
    }
    @Override
    public IStates conversao(int velho, int novo, int numero)
    {
        dataGame.conversao(velho,novo,numero);
        return  this;
    }
    @Override
    public IStates upgrade_cargo()
    {
        dataGame.upgrade_cargo();
        return this;
    }
}
