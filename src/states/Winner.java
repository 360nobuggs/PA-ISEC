package states;


import logica.DataGame;

public class Winner extends StateAdapter {
    public Winner(DataGame dataGame) {
        super(dataGame);

    }

    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WINNING;
    }

    @Override
    public IStates novo_jogo() {
        dataGame.clearMsgLog();
        return new PickShip(dataGame);
    }

}