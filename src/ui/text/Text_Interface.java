package ui.text;
import data.StateMachineGame;
import logica.*;
import data.*;
import states.*;

import java.util.Scanner;
public class Text_Interface {
    Boolean ss_flag= false;
    private Scanner sc;
    private StateMachineGame game;
    //private Game game;
    public  Text_Interface(StateMachineGame gameState) {
        this.game = gameState;
        this.sc = new Scanner(System.in);
        //this.game = new Game(DATA);
    }

    public void run() {
        while (!(game.getState() instanceof GameOver)&&!(game.getState() instanceof Winner)) {

          display_log();

           // System.out.println(game.getGame().toString());

            if (game.getState() instanceof PickShip)
                 Choose_ship();
            else if(game.getState() instanceof SpaceEx)
                Space_Exploration();
            else if(game.getState() instanceof SpaceStation)
                space_station();
            else if (game.getState() instanceof PlanetExploration)
                planet_exploration();
        }
    }
private void display_log()
{
    if (game.getGame().getMsgLog().size() > 0) {
        System.out.println("");

        game.getGame().getMsgLog().forEach((msg) -> {
            System.out.println("--> " + msg);
        });

        game.getGame().clearMsgLog();
    }
}

 private void Choose_ship()
 {
     int j;
     System.out.println("Que navio deseja ?");
     System.out.println("1 = militar");
     System.out.println("2 = mineiro");
     while(!sc.hasNextInt())
         sc.next();

     j= sc.nextInt();
     game.ship(j);//transição de estado 1 para 2
     //game.ship(j);
     System.out.println("Navio criado!");
 }

 private void Space_Exploration() {
     //eventos
     //bloco vitória /derrota
     //game.getState().win_loss_check();
         if (game.getGame().space_station_prob() || ss_flag==true) {
             game.getGame().generate_planet();
             System.out.println("|---------------------------------------------------------------------------------------|");
             game.getGame().getship().show_resorces();
             System.out.println("|Fuel: "+ game.getGame().getship().getFuel()+" Shield: "+ game.getGame().getship().getShield()+" Ammo: "+ game.getGame().getship().getBalas()+ " Artifacts : "+ game.getGame().getship().getArtefatos()+" Trip: "+game.getGame().getship().getTripulacao());
             System.out.println("O rescurso do proximo planeta é " + game.getGame().conversao_nome_numero_p(game.getGame().getPlaneta_auxiliar().getTipo()) + " e possui um tesouro do tipo: " + game.getGame().getPlaneta_auxiliar().getTreasure());
             System.out.println("Deseja parar ou seguir viagem ?");
             System.out.println("p: parar");
             System.out.println("c: continuar");
             String answer = "";
             while (!answer.equalsIgnoreCase("p") && !answer.equalsIgnoreCase("c")) {
                 answer = sc.nextLine();
             }
             if (answer.charAt(0) == 'p') {
                 //vamos explorar
                 //game.mover(1);
             }
             if (game.getGame().getship().getTripulacao() == 6) // se tiver cargo officer
             {
                 convert_resorces();
                 game.getGame().addMsgLog("recursos convertidos");
             }
             ss_flag= false;//evitar space stations sucessivas
         } else {
             System.out.println("space station ");
             ss_flag= true;
             //game.mover(2);
         }
     //game.mover(0);
     }

     private void convert_resorces()
     {
         System.out.println("Deseja converter recursos em escudo, balas ou combustivel ?");//converção de recursos em materiais
         System.out.println("y: sim");
         System.out.println("n: nao");
         String answer="";
         while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
             answer = sc.nextLine();
         }
         int j=0;
         if (answer.charAt(0) == 'y') {
             while(j!=4) {
                 game.getGame().getship().show_resorces();
                 System.out.println("Que item deseja ?");
                 System.out.println("1. Escudo(preto, verde, azul)");
                 System.out.println("2. Ammo (preto, azul)");
                 System.out.println("3. Combustivel (azul, vermelho, verde)");
                 System.out.println("4. Terminar conversao");
                 j= sc.nextInt();
                 if (!game.getGame().conv_rec_space(j))
                     System.out.println("Sem recursos os suficientes.");
             }

         }
     }
     // Space station graphic methods
     private void space_station()
     {
         game.getGame().addMsgLog("Chegou a estacao de servico");
         System.out.println("Bem-vindo á estação de serviço!");
         game.getGame().getship().show_resorces(); //mostra recursos
         convert();
         upgrade_cargo();
         if (!game.getGame().getship().getDrone()) {
             purchase_drone();
         }
         if (game.getGame().getship().getTripulacao() < 6) {
             purchase_crew();
         }
        // refill();
         game.novo_turno();
     }
    private void upgrade_cargo() { //upgrade cargo
        String answer = "";
        System.out.println("Pertente dar upgrade ao cargo ?");
        System.out.println("y =sim");
        System.out.println("n =nao");
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            answer = sc.nextLine();
        }
        if (answer.charAt(0) == 'y') {
           /* if(game.getGame().upgrade_cargo()== true)
            {
                System.out.println("upgrade concluído");
                game.getGame().addMsgLog("Cargo upgraded");
            }
            else{System.out.println("Nao tem recursos os suficientes");
                game.getGame().addMsgLog("Cargo not upgraded");
            }*/
        }
    }
    private void purchase_drone() { //substituir drone
       /* String answer = "";
        System.out.println("Pertente dar comprar um drone novo ?");
        System.out.println("y =sim");
        System.out.println("n =nao");
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            answer = sc.nextLine();
        }
        if (answer.charAt(0) == 'y')
            if ((game.getGame().buy_drone()) ){
                System.out.println("Têm o novo drone XP2000!");
                game.getGame().addMsgLog("Novo drone");
            } else {
                System.out.println("Nao tem recursos os suficientes");
                game.getGame().addMsgLog("Continua sem drone");
            }*/
    }
    public void purchase_crew() { //comprar tripulação
        String answer = "";
        System.out.println("Pertente obter um novo membro da tripulacao ?");
        System.out.println("y =sim");
        System.out.println("n =nao");
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            answer = sc.nextLine();
        }
        /*if (answer.charAt(0) == 'y')
            if (game.getGame().buy_tripulacao()) {
                game.getGame().addMsgLog("Tripulante recrutado");
                System.out.println("Um mercenario aproxima-se para se juntar a aventura");
            } else {
                System.out.println("Nao tem recursos os suficientes");
            }*/
    }
    private void convert()
    {
        String answer = "";
        String velho = "";
        String novo = "";
        boolean repeat = true;
        int q = 0;
        System.out.println("Pertente converter um recurso ?");
        System.out.println("y =sim");
        System.out.println("n =nao");
        answer = sc.nextLine();
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) { //converção de multiplos recursos
            answer = sc.nextLine();
        }
        if (answer.charAt(0) == 'y') {
            while (repeat) {
                System.out.println("Que recurso converter ?");
                System.out.println("azul preto vermelho verde");
                while (!velho.equalsIgnoreCase("azul") && !velho.equalsIgnoreCase("preto") && !velho.equalsIgnoreCase("vermelho") && !velho.equalsIgnoreCase("verde")) {
                    velho = sc.nextLine();
                }
                System.out.println("Que recurso deseja ?");
                System.out.println("azul preto vermelho verde");
                while (!novo.equalsIgnoreCase("azul") && !novo.equalsIgnoreCase("preto") && !novo.equalsIgnoreCase("vermelho") && !novo.equalsIgnoreCase("verde")) {
                    novo = sc.nextLine();
                }
                System.out.println("Quantos quer converter ?");
                q = sc.nextInt();
                game.getGame().conversao(game.getGame().conversao_nome_numero(velho), game.getGame().conversao_nome_numero(novo) ,q);
                System.out.println("Pertente converter mais algum ?");
                System.out.println("y =sim");
                System.out.println("n =nao");
                answer = "";
                game.getGame().addMsgLog("Recurso convertido");
                while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                    answer = sc.nextLine();
                }
                if (answer.charAt(0) == 'n') {
                    repeat = false;

                }
            }
        }
    }
    /*
    private void refill() { //recarrega escudo, pena nao dar para meter combustivel tambem
        String answer = "";
        System.out.println("Pertente recarregar escudo ?");
        System.out.println("y =sim");
        System.out.println("n =nao");
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            answer = sc.nextLine();
        }

        if (answer.charAt(0) == 'y') {
            if (game.getGame().refill()) {
                game.getGame().addMsgLog("Escudo reparado");
                System.out.println("O navio esta como novo chefe.");
            } else {
                System.out.println("Nao tem recursos os suficientes");
                game.getGame().addMsgLog("Escudo não reparado");
            }
        }
    }*/
    private void planet_exploration()
    {
        game.getGame().vis_combate();
        int a[];// serve para print de coordenadas do drone
        int b[]=game.getGame().getPlaneta_auxiliar().getPosInTresure();// posição do tesouro/recurso
        boolean got_tresure=false;
        boolean first_move=false; // no primeiro movimento ele vai criar as coordenadas da nave
        while(!game.getGame().getPlaneta_auxiliar().returned(first_move,got_tresure))
        {
            if(game.getGame().check_treasure())
            {
                got_tresure=true;
                System.out.println("Tesouro obtido!");
            }
            ;
            if(!first_move){game.getGame().getPlaneta_auxiliar().firstMove();first_move=true;}
            if(!game.getGame().check_combat())
            {
                //pode mover-se
                move();
                game.getGame().move_alien();
                game.getGame().vis_combate();
            }
            else {
                //combate
                if(!game.getGame().combat())
                {
                    System.out.println("Drone destruido.");
                    System.out.println("A voltar para o navio");
                    game.novo_turno();
                }
            }
            game.getGame().coordenadas();

        }
        game.getGame().addMsgLog("Exploração foi um sucesso");
        System.out.println("Exploração foi um sucesso");
        System.out.println("Recurso "+game.getGame().getPlaneta_auxiliar().getTreasure()+" obtido!");
        game.getGame().add_resorce();
        if(game.getGame().getPlaneta_auxiliar().getTreasure()=="artifact")
        {
            game.getGame().addMsgLog("Artefacto acrescentado");
             //depois de um artefacto nao deve poder continuar
            game.novo_turno();
        }
        if(explore_again())
        {
            planet_exploration();
        }
        else
        {
            game.novo_turno();

        }
    }


    private boolean explore_again() // pode explorar varias vezes enquanto que houver recursos no planeta
    {
        String answer="";
        //game.getGame().show_resorces();
        System.out.println("Deseja explorar novamente");
        System.out.println("y: sim");
        System.out.println("n: nao");
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            answer = sc.nextLine();
        }
        if(answer.charAt(0)=='y')
        {
            if(game.getGame().getPlaneta_auxiliar().posso_minar())
            {
                System.out.println("O drone foi enviado.");
                game.getGame().getPlaneta_auxiliar().settup(true);
                return true;
            }
            else{
                System.out.println("A voltar para o navio.");
                //volta para o espaço
                return  false;
            }
        }
        else{
            //volta para o espaço
            return false;
        }
    }







    public void move()
    {
        game.getGame().addMsgLog("Drone move-se"); //a nomenclatura dos comandos depois vai ser alterada para wasd para facilitar movimento
        String answer="";
        System.out.println("Para onde se deve mover o drone");
        System.out.println("u: para cima");
        System.out.println("d: para baixo");
        System.out.println("r: direita");
        System.out.println("l: esquerda");
        while (!answer.equalsIgnoreCase("u") && !answer.equalsIgnoreCase("d")&& !answer.equalsIgnoreCase("r")&& !answer.equalsIgnoreCase("l")) {
            answer = sc.nextLine();
        }
        game.getGame().move(answer.charAt(0));
    }
 }









