package states;

import logica.DataGame;

public class PickShip extends StateAdapter {
    public PickShip(DataGame datagame)
    {
        super(datagame);
    }

    @Override
    public IStates space_ex()
    {
        return new SpaceEx(dataGame);
    }
    public IStates ship(int s){//transição de estado
        getDataGame().setship(s);
        getDataGame().generate_planet();
        getDataGame().datadisplay();
        return  space_ex();
    };
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_PICKSHIP;
    }
}
