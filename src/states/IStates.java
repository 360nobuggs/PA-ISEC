package states;

import java.io.Serializable;

public interface IStates extends Serializable {
    IStates ship(int s);
    IStates winner();
    IStates win_loss_check();
    IStates game_over();
    IStates space_station();
    IStates space_ex();
    IStates planet_ex();
    IStates novo_jogo();
    IStates buy_trip();
    IStates buy_drone();
    IStates refill();
    IStates convert_space(int escolha);
    IStates upgrade_cargo();
    IStates conversao(int velho, int novo, int numero);
    IStates movimento(char direcao);
    IStates add_res();
    IStates explorar_novamente();
    InteracaoEsperada getInteracaoEsperada();

}
