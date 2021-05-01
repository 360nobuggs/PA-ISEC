package states;

import logica.DataGame;

public class PlanetExploration extends StateAdapter {
    public PlanetExploration(DataGame data)
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
        return InteracaoEsperada.INTERACAO_PLANET_EX;
    }

    @Override
    public IStates movimento(char direcao) {
        if(getDataGame().movimento(direcao))
        {
            return this;
        }
        else {
            return space_ex();
        }
    }
    @Override
    public IStates add_res()
    {
        dataGame.add_resorce();
        return this;
    }
    @Override
    public IStates explorar_novamente()
    {
        if(getDataGame().getPlaneta_auxiliar().getTreasure()=="artifact"||!dataGame.explorar_novamente())
        {
            return space_ex();
        }
        else
        {
            getDataGame().mensagem_clear();
            getDataGame().inicio_planeta();
            return this;
        }
    }

}
