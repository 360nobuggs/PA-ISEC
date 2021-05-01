package states;
import data.StateMachineGame;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;

public class Maquina_observavel {
    private PropertyChangeSupport propertyChangeSupport;
    private StateMachineGame MaqEstados;
    private int old_value=9;//variavel auxiliar á visualização space_station
    public Maquina_observavel(StateMachineGame MaqEstados) {
        this.MaqEstados = MaqEstados;
        propertyChangeSupport = new PropertyChangeSupport(MaqEstados);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    // funcoes de atualizacao da informacao
    public void pickship(int j) {
        // a logica executa a atualizacao dos dados
        this.MaqEstados.ship(j);
        propertyChangeSupport.firePropertyChange( null, null, true);
    }
    public void space_Exploration()
    {
        this.MaqEstados.novo_turno();
        propertyChangeSupport.firePropertyChange(null, null, true);
    }

    public void planet_ex()
    {
        MaqEstados.planet_ex();
        propertyChangeSupport.firePropertyChange(null, null, true);
    }
    public void novo_jogo()
    {
        MaqEstados.novo_jogo();
        propertyChangeSupport.firePropertyChange(null, null, true);
    }
    //CHECKERS----------------------------------------------------------------------------------------------------------------------------
    public Boolean pode_converter()
    {
        return MaqEstados.pode_converter();
    }
    public Boolean tem_drone(){return MaqEstados.tem_drone();}
    public Boolean tripulacao_cheia(){return MaqEstados.tripulacao_cheia();}
    public ArrayList<int[]> get_pos(){return MaqEstados.get_pos();}
    public Boolean check_tresure() { return MaqEstados.check_treasure(); }
    public Boolean termina_exploracao() { return MaqEstados.termina_exploracao(); }
    //STATE METHODS
    public void upgrade_cargo ()
    {
        MaqEstados.upgrade_cargo();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void refill()
    {
        MaqEstados.refill();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void buy_drone()
    {
        MaqEstados.buy_drone();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void buy_trip()
    {
        MaqEstados.buy_trip();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void conversao(int velho, int novo, int numero)
    {
        MaqEstados.conversao(velho,novo,numero);
        old_value=9;
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public int get_old_value()
    {
        return old_value;
    }
    public void setOld_value(int old)
    {
        old_value=old;
    }

    public void conv_space(int escolha)
    {
        MaqEstados.convert_space(escolha);
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void movimento(char escolha)
    {
        MaqEstados.movimento(escolha);
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void explorar_novamente()
    {
        MaqEstados.explorar_novamente();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public void add_res()
    {
        MaqEstados.add_res();
    }
    public String MsgLog() {
        return MaqEstados.LogToString();
    }
    public InteracaoEsperada getInteracaoEsperada() {
        return MaqEstados.getInteracaoEsperada();
    }
    //Save load------------------------------------------------
    public StateMachineGame getMaqEstados()
    {
        return MaqEstados;
    }
    public void setMaqEstados(StateMachineGame game)
    {
        this.MaqEstados=game;
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    public static void saveGameToFile(File file, Object o) throws IOException
    {
        ObjectOutputStream oout = null;

        try{
            oout = new ObjectOutputStream(new FileOutputStream(file));
            oout.writeObject(o);

        }finally{
            if(oout != null)
                oout.close();
        }
    }

    public static Object retrieveGameFromFile(File file) throws IOException, ClassNotFoundException
    {
        ObjectInputStream oin = null;

        try{
            oin = new ObjectInputStream(new FileInputStream(file));
            return oin.readObject();
        }finally{
            if(oin != null)
                oin.close();
        }
    }

}
