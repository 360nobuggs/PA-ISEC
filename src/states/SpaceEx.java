package states;

import logica.DataGame;

public class SpaceEx extends StateAdapter{
    public SpaceEx(DataGame datagame)
    {
        super(datagame);

    }

    @Override
    public IStates game_over() {
        return new GameOver(getDataGame());
    }
    @Override
    public IStates winner(){return new Winner(getDataGame());}
    @Override
    public IStates win_loss_check()
    {
        if (dataGame.getship().getFuel()<=0 || (dataGame.getship().getTripulacao() == 0)) {
            getDataGame().clearMsgLog();
            return game_over();
        }
        if(dataGame.getship().getArtefatos()==5)
        {
            getDataGame().clearMsgLog();
            return winner();
        }
        return this;
    }
    @Override
    public IStates space_station()
    {
        dataGame.welcome_sp();
        return new SpaceStation(dataGame);
    }
    @Override
    public IStates planet_ex()
    {
        if((dataGame.getship().getDrone()) && (dataGame.getship().getTripulacao() >= 3))
        {
            getDataGame().mensagem_clear();
            getDataGame().inicio_planeta();
            return new PlanetExploration(dataGame);
        }
       else
        {
            return  this;
        }
    }
    @Override
    public IStates space_ex()
    {
        if(dataGame.space_station_prob())
        {
            return space_station();
        }
        else
        {
            getDataGame().mensagem_clear();
            getDataGame().logica_space_ex();
            getDataGame().generate_planet();
            getDataGame().datadisplay();
            return this;
        }
    }
    @Override
    public IStates convert_space(int escolha)
    {
        dataGame.conv_rec_space(escolha);
        return  this;
    }

    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_SPACE_EX;
    }
}
