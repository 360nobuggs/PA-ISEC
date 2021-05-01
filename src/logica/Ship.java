package logica;

import java.io.Serializable;

public class Ship implements Serializable {
    private int drone_toughtness=6;
    boolean id = false;
    protected int fuel;
    protected int balas;
    protected int shield;
    protected int artefatos;
    protected int tripulacao;
    protected int upgrade = 0;
    protected int cargo_limit;
    protected int[] cargo = {0, 0, 0, 0};// {azul ,preto , vermelho , verde}
    protected boolean drone = true;

    public Ship() {
        this.fuel = 0;
        this.artefatos = 0;
        this.shield = 0;
        this.balas = 0;
        this.tripulacao = 6;
        this.upgrade = 0;
        this.cargo_limit = 6;

    }
    public void setTrip(int o)
    {
        tripulacao=o;
    }
    public int getFuel() {
        return fuel;
    }

    public int getArtefatos() {
        return artefatos;
    }

    public int getBalas() {
        return balas;
    }

    public int getTripulacao() {
        return tripulacao;
    }

    public void killTripulacao(){tripulacao= tripulacao--;}

    public boolean getTipo() {
        return id;
    }
    public int[] getCargo() {
        return cargo;
    }

    public void upgrade() {
        cargo_limit=cargo_limit+6;
    }

    ;

    public void artefacto_add() {
        artefatos++;
    }

    public void add_resorce(int resorce) {
        /*switch (resorce) {
            case "blue":
                if (cargo[0] < cargo_limit) {
                    cargo[0] = cargo[0]++;
                }
                break;
            case "black":
                if (cargo[1] < cargo_limit) {
                    cargo[1] = cargo[1]++;
                }
                break;
            case "red":
                if (cargo[2] < cargo_limit) {
                    cargo[2] = cargo[2]++;
                }
                break;
            case "green":
                if (cargo[3] < cargo_limit) {
                    cargo[3] = cargo[3]++;
                }
                break;*/
        if (resorce==0||resorce==1||resorce==2||resorce==3)
        {
            cargo[resorce]=cargo[resorce]+1;
        }
        else
        {
            artefacto_add();
        }
        }




    public boolean getDrone() {
        return drone;
    }

    public void remove_resorce(String resorce) { //remove recursos
        switch (resorce) {
            case "blue":
                if (cargo[0] > 0) {
                    cargo[0] = cargo[0]--;
                }
                break;
            case "black":
                if (cargo[1] > 0) {
                    cargo[1] = cargo[1]--;
                }
                break;
            case "red":
                if (cargo[2] > 0) {
                    cargo[2] = cargo[2]--;
                }
                break;
            case "green":
                if (cargo[3] > 0) {
                    cargo[3] = cargo[3]--;
                }
                break;
        }
    }
    public void setFuel(int n)
    {
        fuel= n;
    }
    // {azul ,preto , vermelho , verde}
    public boolean pay_up_upgrade(int money) {
        if (cargo[0] >= money && cargo[1] >= money && cargo[3] >= money && cargo[3] >= money) { // funções de pagamento
            cargo[0] = cargo[0] - money;
            cargo[1] = cargo[1] - money;
            cargo[2] = cargo[2] - money;
            cargo[3] = cargo[3] - money;
            return true;
        } else {
            return false;
        }
    }
    public boolean pay_up_shield(int money) {
        if (cargo[0] >= money && cargo[1] >= money && cargo[3] >= money) {
            cargo[0] = cargo[0] - money;
            cargo[1] = cargo[1] - money;
            cargo[3] = cargo[3] - money;
            return true;
        } else {
            return false;
        }
    }
    public boolean pay_up_ammo(int money) {
        if (cargo[0] >= money && cargo[1] >= money) {
            cargo[0] = cargo[0] - money;
            cargo[1] = cargo[1] - money;
            return true;
        } else {
            return false;
        }
    }
    public boolean pay_up_fuel(int money) {
        if (cargo[1] >= money && cargo[3] >= money && cargo[3] >= money) {
            cargo[1] = cargo[1] - money;
            cargo[2] = cargo[2] - money;
            cargo[3] = cargo[3] - money;
            return true;
        } else {
            return false;
        }
    }
    public void addmember()
    {
        if(tripulacao<6) {
            tripulacao = tripulacao++;
        }
    }

    public int got_Drone() {
        if(drone){
            return 1;
        }
        return 0;
    }

    public String show_resorces() {
       // System.out.println("[---------------------------------]");
        //System.out.println("Os recursos disponiveis sao: ");
       return "|recurso azul: " + cargo[0]+" recurso preto: " + cargo[1]+" recurso vermelho: " + cargo[2]+ " recurso verde: " + cargo[3];
       // System.out.println("recurso preto: " + cargo[1]);
       // System.out.println("recurso vermelho: " + cargo[2]);
       // System.out.println("recurso preto: " + cargo[3]);
       // System.out.println("[---------------------------------]");
    }

    public boolean damage_drone()
    {
        if(drone_toughtness>0)
        {
            drone_toughtness--;
            return true;
        }
        else {
            drone= false;
            return false;
        }
    }
    public void change_drone() {
        drone = !drone;
        drone_toughtness=6;
    }
    public int getShield(){return shield;}
    public Boolean conversao(int velho, int novo, int quantidade) {
        if (quantidade <= cargo[velho]) {
            cargo[velho] = cargo[velho] - quantidade;
            cargo[novo] = cargo[novo] + quantidade;
            return true;
            //show_resorces();
        } else {
            return  false;
        }
    }

    public void shield_up() { shield++;}
    public void ammo_up() { balas++;}
    public void fuel_up() { fuel++;}
    public boolean expend_fuel(int n) {
        if (fuel > 0) {
            if (fuel >= n) {
                fuel = fuel - n;
            }
            return true;
        } else
        {
            return false; //se em exploração nao deixar explorar mais
        }
    }
    public boolean expend_shield(int n) {
        if (shield >= n) {
                shield = shield - n;
            return true;
        } else
        {
            return false;
        }
    }


}
