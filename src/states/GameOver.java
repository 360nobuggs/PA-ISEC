package states;


import logica.DataGame;

public class GameOver extends StateAdapter{
    public GameOver(DataGame dataGame)
    {
        super(dataGame);
    }
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_GAMEOVER;
    }
    @Override
    public IStates novo_jogo()
    {
        dataGame.clearMsgLog();
        return new PickShip(dataGame);
    }
}
