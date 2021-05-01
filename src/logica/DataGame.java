package logica;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataGame implements Serializable {
    private Ship ship= new Ship(); //navio
    private Planet planeta_auxiliar;
    private Boolean created= false;
    String mensagem="";
    public DataGame() {
        init();
    }

    public void init() {

    }

    public void setship(int s) {
        if(s==1)
        {
            ship = new Military();
        }
        else
        {
            ship= new Mining();
        }
    }
    private List<String> msgLog = new ArrayList<>();; //log
    public Ship getship() {
        return ship;
    }

    public int conversao_nome_numero(String nome) { //converte nome de recursos na posiçao em que eles estao no array de cargo
        switch (nome) {
            case "azul":
                return 0;
            case "preto":
                return 1;
            case "vermelho":
                return 2;
            case "verde":
                return 3;
            case "artifact":
                return 4;
            default:
                return 0;
        }


    }
    public void clearMsgLog()
    {
        msgLog.clear();
    }
    public List<String> getMsgLog(){return msgLog;}
    public void addMsgLog(String msg)
    {
        msgLog.add(msg);
    }
    public String conversao_nome_numero_p(int o) { //converte inteiros no nome do tipo de planeta
        switch (o) {
            case 1:
                return "azul";
            case 2:
                return "preto";
            case 3:
                return "vermelho";
            case 4:
                 return "verde";
            default:
                return "artifact";
        }
    }

    public Planet getPlaneta_auxiliar()
    {
        return  planeta_auxiliar;
    } //planeta
    public void setPlaneta_auxiliar(Planet p)
    {
        planeta_auxiliar= p;
    }

    //SPACE EXPLORATION METHODS
    public void logica_space_ex()//Logica por trás dos eventos aliatórios
    {
        D10 dado= new D10(8);
        if(dado.roll()==1)
        {
            addMsgLog("O navio é sugado por um buraco negro.");
            buraco_negro();
        }
        else
        {
            Evento();
        }
    }
    public boolean pode_converter()
    {
        if (getship().getTripulacao() == 6)
        {
            return true;
        }
        return false;
    }
    public void datadisplay()
    {
        //addMsgLog("|---------------------------------------------------------------------------------------|");
        recursos_display();
        addMsgLog("O rescurso do proximo planeta é " +conversao_nome_numero_p(getPlaneta_auxiliar().getTipo()) + " e possui um tesouro do tipo: " + getPlaneta_auxiliar().getTreasure());
        addMsgLog("Deseja parar ou seguir viagem ?");
    }
    public void recursos_display()
    {
        addMsgLog(getship().show_resorces());
        addMsgLog("|Fuel: "+ getship().getFuel()+" Shield: "+getship().getShield()+" Ammo: "+getship().getBalas()+ " Artifacts : "+getship().getArtefatos()+" Trip: "+getship().getTripulacao()+" Drone: "+getship().getDrone());
    }
    public void mensagem_clear()
    {
        mensagem= "";
    }
    public String display_log()//converte o log numa String para ser associada a uma label
    {
        if(!msgLog.isEmpty())
        {
            if (getMsgLog().size() > 0) {
                System.out.println("");

                while (!msgLog.isEmpty())
                {
                    mensagem= mensagem+'\n' +msgLog.get(0);
                    msgLog.remove(0);
                }
            }
        }

        return mensagem;
    }
    public void buraco_negro()
    {
        int addfuel=0;
        int addshield=0;
        if(getship().getTripulacao()<4) // 1=capitao 2=navigation party 3=landing party 4=shields 5= weapons 6= cargo
        {
            addfuel++;
            addshield++;
            addshield++;
        }
        if(!getship().expend_fuel(3+addfuel))
        {
            System.out.println("death");
            getship().setFuel(0);
        }
        if(!getship().expend_shield(2+addshield))
        {
            getship().setTrip(getship().getTripulacao()-1);
        }
    }
    public void Evento() {
        //dados
        D10 d6 = new D10(6);
        D10 d2 = new D10(2);
        D10 d3 = new D10(3);
        D10 d4 = new D10(4);
        int a = d6.roll();
        switch (a)
        {
            case 1://crew death
                getship().setTrip(getship().getTripulacao()-1);//morram por amor da santa
                if (d2.roll() == 1) {
                    addMsgLog("Devido a nao ter um cozinheiro a bordo um dos tripulantes morreu de intoxicação alimentar.");
                } else {
                    addMsgLog("Devido a nao ler corretamente a documentação o programador a bordo morreu eletrocutado");
                }
                break;
            case 2://recurso random
                int j = d4.roll();
                getship().add_resorce(j - 1);
                addMsgLog("Foi encontrado um navio perdido.");
                addMsgLog("Esperemos nao encontrar face huggers.");
                addMsgLog("Recurso " + conversao_nome_numero_p(j) + " encontrado.");
                break;
            case 3: // perda de cargo
                int b = d3.roll();
                int u = d4.roll();
                for (int i = 0; i < b; i++) {
                    getship().remove_resorce(conversao_nome_numero_p(u - 1));
                }
                addMsgLog("Alguem deixou a porta aberta.");
                addMsgLog("Algum cargo foi perdido.");
                break;
            case 4: //fuel loss
                getship().expend_fuel(1);
                addMsgLog("Devem ter dado um tiro no deposito!");
                break;
            case 5: //No event
                addMsgLog("Nada a reportar.");
                break;
            case 6:
                if (getship().getTripulacao() < 6) {
                    getship().addmember();
                    addMsgLog("Um novo membro junta-se de um navio cujo combustivel ficou sem combustivel.Ele agradece a generosidade por lhe salvar a vida.");
                } else {
                    addMsgLog("Nao podemos ter mais ninguem no navio.");
                    addMsgLog("A nave sem combustivel flutua lentamente no abisso negro que é o espaço,a sua tripulacao com ela.");
                }
                break;
        }
    }
    public boolean space_station_prob() //gere prob de planeta ou station
    {
        D10 dado= new D10(10);
        if(dado.roll()>3) //menor que 3 é uma space station
        {
            return false;
        }
        else{return true;}//mandar para a estacao
    }

    public void generate_planet()
    {
        getship().expend_fuel(1);
        Planet planeta= new Planet();
        setPlaneta_auxiliar(planeta);
        //addMsgLog("planeta gerado");
    }
    public boolean conv_rec_space(int r)
    {
        mensagem_clear();
        recursos_display();
        switch(r){
            case 1:
                if(getship().pay_up_shield(1))
                {
                    return true;
                }
                else
                {
                    addMsgLog("Sem recursos os suficientes para conversão.");
                    return false;
                }
            case 2:
                if(getship().pay_up_ammo(1))
                {
                    getship().ammo_up();
                    return true;
                }
                else
                {
                    addMsgLog("Sem recursos os suficientes para conversão.");
                    return false;
                }
            case 3:
                if(getship().pay_up_fuel(1))
                {
                    getship().fuel_up();
                    return true;
                }
                else
                {
                    addMsgLog("Sem recursos os suficientes para conversão.");
                    return false;
                }
            default:
                break;
        }
        return true;
    }

    //SPACE STATION METHODS
    public void welcome_sp()
    {
        mensagem_clear();
        //recursos_display();
        addMsgLog("Bem-vindo á estação de serviço!");
        recursos_display();

    }
    public void upgrade_cargo()
    {
        mensagem_clear();
        recursos_display();
        if (getship().pay_up_upgrade(3)) {
            getship().upgrade();
            addMsgLog("Cargo upgraded");
        } else {
            addMsgLog("Sem recursos os suficientes. Volte uma proxima vez.");
        }
    }
    public void buy_drone()
    {
        mensagem_clear();
        recursos_display();
        if(!getship().getDrone())
        {
            if (getship().pay_up_upgrade(3)) {
                getship().change_drone();
                addMsgLog("Têm o novo drone XP2000!");
            } else {
               addMsgLog("Sem recursos os suficientes.");
            }
        }

    }
    public Boolean tripulacao_cheia()
    {
        if (getship().getTripulacao() < 6) {
            return  false;
        }
        return true;
    }
    public void buy_tripulacao() {
        mensagem_clear();
        recursos_display();
        if (!tripulacao_cheia()) {
            if (getship().pay_up_upgrade(1)) {
                getship().addmember();
                addMsgLog("Um mercenario alien X23 aproxima-se para se juntar a aventura.");
            } else {
                addMsgLog("Sem recursos os suficientes");
            }
        }
    }
    public void conversao(int velho, int novo, int numero)
    {
        mensagem_clear();
        if(getship().conversao(velho, novo, numero)){
            recursos_display();
            addMsgLog("Conversão com sucesso");}
        else
        {
            recursos_display();
            addMsgLog("Conversão sem sucesso.");
        }

    }

    public void refill()
    {
        mensagem_clear();
        recursos_display();
        if (getship().pay_up_upgrade(1)) {
            getship().shield_up();
            addMsgLog("Escudos com potência máxima!");
        } else {
           addMsgLog("Sem recursos os suficientes");
        }
    }

    //PLANET EXPLORATION METHODS

    boolean got_tresure=false;
    boolean first_move=false; // no primeiro movimento ele vai criar as coordenadas da nave
    public void inicio_planeta()
    {
        getship().expend_fuel(1);
        addMsgLog("Bem-vindo ao Planeta Alfa-PK3E do Sector MINERVA.");
        got_tresure=false;
        first_move=false;
    }
    public Boolean termina_exploracao()
    {
        return getPlaneta_auxiliar().returned(first_move,got_tresure);
    }
    public boolean check_treasure()
    {
        if(getPlaneta_auxiliar().gotTreasure())
        {
            clearMsgLog();
            //addMsgLog("Recurso obtido! ");
            got_tresure=true;
        }
            return got_tresure;
    }
    //func principal de movimento no planeta
    public Boolean movimento(char u)
    {
        if(!check_combat())
        {
            if(!first_move){getPlaneta_auxiliar().firstMove();first_move=true;}
            move(u);
            move_alien();
            //visualiza o combate
        }
        else
        {
            while(check_combat())
            {
                if(!combat())
                {
                    addMsgLog("Drone destruido.");
                    addMsgLog("A voltar para o navio");
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<int[]> get_mapa()
    {

            ArrayList lista= new ArrayList<int[]>(3);
            lista.add(planeta_auxiliar.getPosInTresure());//tesouro
            lista.add(planeta_auxiliar.getPosShip());//ship
            lista.add(planeta_auxiliar.getPosDrone());//drone
            lista.add(planeta_auxiliar.getPostAlien());//alien
            return lista;
    }


    public boolean check_combat()
    {
        if(getPlaneta_auxiliar().combate_init())
        {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean combat()
    {
        clearMsgLog();
        addMsgLog("Combate inicado");
        if(getPlaneta_auxiliar().combate())//o drone recebe dano
        {
            addMsgLog("Defesa comprometida");
            if(!getship().damage_drone())
            {
                getship().setTrip(getship().getTripulacao()-1);
                addMsgLog("Exploração falhou, Drone destruido.");
                return false; //drone destruido
            }
            return true; //está em combate
        }
        return true;
    }

    public void add_resorce()
    {
        getship().add_resorce(conversao_nome_numero(getPlaneta_auxiliar().getTreasure()));
        clearMsgLog();
        addMsgLog("Recurso "+getPlaneta_auxiliar().getTreasure()+" obtido!");
    }
    public Boolean explorar_novamente()
    {
       if(getPlaneta_auxiliar().posso_minar())
       {
           getPlaneta_auxiliar().settup(true);
           return true;
       }
       else
       {
           addMsgLog("Os recursos do planeta estao esgotados.");
           return false;
       }

    }

    //funcoes velhas
    public void visualizacao(int[][] mapa)
    {
        System.out.println("/------------------------------------------------------------------/");
        for(int i=0;i<mapa.length;i++)
        {

            for(int j=0;j<mapa.length;j++)
            {

                switch(mapa[i][j])
                {
                    case 1:
                        System.out.print('D');
                        break;
                    case 2:
                        System.out.print('A');
                        break;
                    case 3:
                        System.out.print('T');
                        break;
                    case 4:
                        System.out.print('S');
                        break;
                    default:
                        System.out.print('X');
                }
            }
            System.out.println("");
        }
        System.out.println("/------------------------------------------------------------------/");
        System.out.println("");
    }

    public void move(char a)
    {
        getPlaneta_auxiliar().moveDrone(a);
    }
    public void move_alien()
    {
        getPlaneta_auxiliar().move_Alien();
    }
    public void vis_combate()
    {
        visualizacao(getPlaneta_auxiliar().getTerreno());
    }
    public void coordenadas()
    {
        int a[]=getPlaneta_auxiliar().getPosInDrone();
        int b[]=getPlaneta_auxiliar().getPosInTresure();
        System.out.println(a[0]+" "+ a[1]+" | "+b[0]+" "+ b[1]+" ");
    }
}