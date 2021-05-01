package data;

import logica.*;
import states.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StateMachineGame implements Serializable
    {
        private DataGame dataGame;
        private IStates state;
        public StateMachineGame()
        {

            dataGame = new DataGame(); //inicia o primeiro estado
            state = new PickShip(dataGame);
        }

        public DataGame getGame() {
            return dataGame;
        }
        public void setGame(DataGame game) {
            this.dataGame = game;
        }

        public IStates getState() {

            return state;
        }
        private void setState(IStates state) {

            this.state = state;
        }
        public List<String> getMsgLog()
        {
            return dataGame.getMsgLog();
        }
        public void clearMsgLog()
        {
            dataGame.clearMsgLog();
        }

        public void ship (int s)//Transita para Space ex
        {
            setState(getState().ship(s));
        }

        public void novo_turno()
        {
            setState(state.space_ex());
            setState(state.win_loss_check());//verifica vitoria e derrota
        }
        public void planet_ex()
        {
            setState(getState().planet_ex());
        }
        public InteracaoEsperada getInteracaoEsperada() {
            return state.getInteracaoEsperada();
        }
        public String LogToString() {
            return dataGame.display_log();
        }
        //CHECKERS--------------------------------------------------------------------------------------------------
        public Boolean pode_converter()
        {
            return dataGame.pode_converter();
        }
        public Boolean tem_drone(){return dataGame.getship().getDrone();}
        public Boolean tripulacao_cheia(){return dataGame.tripulacao_cheia();}
        public ArrayList<int[]> get_pos(){return dataGame.get_mapa(); }
        public boolean check_treasure() {return dataGame.check_treasure();}
        public Boolean termina_exploracao() { return dataGame.termina_exploracao(); }
        //METODOS DE ESTADO

        public void movimento(char direcao)
        {
            setState(state.movimento(direcao));
        }
        public void novo_jogo(){setState(state.novo_jogo());}
        public void convert_space(int escolha)
        {
            setState(state.convert_space(escolha));
        }
        public void refill()
        {
            setState(state.refill());
        }
        public void upgrade_cargo()
        {
            setState(state.upgrade_cargo());
        }
        public void buy_drone()
        {
            setState(state.buy_drone());
        }
        public void buy_trip()
        {
            setState(state.buy_trip());
        }
        public void conversao(int velho, int novo, int numero)
        {
            setState(state.conversao(velho,novo,numero));
        }
        public void explorar_novamente()
        {
            setState(state.explorar_novamente());
        }
        public void add_res()
        {
            setState(state.add_res());
        }


    }
