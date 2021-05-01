package logica;

import java.io.Serializable;

public class Planet implements Serializable {
    D10 dado4 = new D10(4);// 0 not in bounds
    D10 dado6 = new D10(5);
    D10 dado2 = new D10(2);

    private int tipo = dado4.roll(); // 1 azul 2 preto 3 vermelho 4 verde
    private int[][] terreno = new int[6][6]; // mapa
    private int xdrone = dado6.roll();
    private int ydrone = dado6.roll();//erro out of bounds
    private int xres = dado6.roll();
    private int yres = dado6.roll();
    private int minar=0;
    private String treasure;
    private Alien alien;
    private int xalien;
    private int yalien;
    private int xini=0;
    private int yini=0;
    public Planet() {
        prob_settup();
        settup(false);
    }

    public void prob_settup() //settup planeta
    {
        tipo = dado4.roll(); // 1 azul 2 preto 3 vermelho 4 verde
        xdrone = dado6.roll();
        ydrone = dado6.roll();//erro out of bounds
        xres = dado6.roll();
        yres = dado6.roll();

    }

    public int getTipo() {
        return tipo;
    }
    public int[][] getTerreno()
    {
        return terreno;
    }
    public String getTreasure()
    {
        return treasure;
    }
    public int[] getPosInDrone()
    {
        int aux[]={xdrone,ydrone};
        return aux;

    }
    public void firstMove() //define a localização do navio
    {
        xini=xdrone;
        yini=ydrone;
    }
    public void setAlien()// 1 azul 2 preto 3 vermelho 4 verde
    {
        switch (tipo) {//define o tipo de alien de acordo com o tipo de planeta
            case 1:
                Alien_azul alien_azul = new Alien_azul();
                alien=alien_azul;
                break;
            case 2:
                Alien_preto alien_preto = new Alien_preto();
                alien=alien_preto;
                break;
            case 3:
                Alien_vermelho alien_vermelho = new Alien_vermelho();
                alien=alien_vermelho;
                break;
            case 4:
                Alien_verde alien_verde = new Alien_verde();
                alien=alien_verde;
                break;
        }
        xalien = dado6.roll();
        yalien = dado6.roll();
        while ((xalien== xdrone && yalien == ydrone)&&(xalien== xres && yalien == yres)) {
            xalien = dado6.roll();
            yalien = dado6.roll();
        }
    }
    public int[] getPosInTresure()
    {
        int aux1[]={xres,yres};
        return aux1;
    }
    public int[] getPosDrone()
    {
        int aux1[]={xdrone,ydrone};
        return aux1;
    }
    public int[] getPosShip()
    {
        int aux1[]={xini,yini};
        return aux1;
    }
    public int[] getPostAlien()
    {
        int aux1[]={xalien,yalien};
        return aux1;
    }
    public boolean combate_init()//se o alien esta numa posição adjacente ao drone, inicia o combate
    {
        if((xdrone+1==xalien&&ydrone==yalien)||(xdrone==xalien&&ydrone+1==yalien)||(xdrone-1==xalien&&ydrone==yalien)||(xdrone==xalien&&ydrone-1==yalien))
        {
            return true;
        }
        else{
        return false;}
    }
    public boolean combate()
    {
        if(alien.attack())
        {
            // ("Defesa comprometida"); //drone danificado
            return true;
        }
        if(alien.death())
        {
            terreno[xalien][yalien]=0;
            setAlien();
            //System.out.println("Alien Destruido.");//mata o alien
        }
        return false;
    }

    /*
    public void setPosInDrone(int pos[])
    {
        System.out.println(xdrone+" "+ydrone);
        terreno[xdrone][ydrone] = 0;
        xdrone= pos[1];
        ydrone= pos[0];
        terreno[xdrone][ydrone] = 1;
        System.out.println(xdrone+"  "+ydrone);
    }*/
    public void moveDrone(char s)//move o drone de acordo com a instrução dada no estado
    {
        //int aux[]=getPosInDrone();
        terreno[xdrone][ydrone] = 0;
        switch(s)
        {
            case 'u':
                if(xdrone==0)
                {
                    //System.out.print("Beep boop you cannot pass this spot.");
                }
                else
                {
                    xdrone=xdrone-1;

                }
                break;

            case 'd':
            if(xdrone==5)
            {
               // System.out.print("Beep boop you cannot pass this spot.");
            }
            else
            {
                xdrone=xdrone+1;

            }
            break;
            case 'r':
                if(ydrone==5)
                {
                   // System.out.print("Beep boop you cannot pass this spot.");
                }
                else
                {
                    ydrone=ydrone+1;

                }
                break;
            case 'l':
                if(ydrone==0)
                {
                    //System.out.print("Beep boop you cannot pass this spot.");
                }
                else
                {
                    ydrone=ydrone-1;

                }
                break;
        }
        terreno[xdrone][ydrone] = 1;
        terreno[xini][yini]=4;//S
    }
    public boolean gotTreasure()
    {
        if((xdrone==xres)&&(ydrone==yres))
        {
            terreno[xres][yres]=1;
            return true;
        }
        else{return false;}
    }
    public boolean returned(boolean allow1, boolean allow2)// se estiver no navio e tiver o tesouro pode voltar, condição de controlo de exploração
    {
        if(allow1&&allow2)
        {
            if((xdrone==xini)&&(ydrone==yini))
            {
                return true;
            }
        }
        return false;
    }
    public void settup(boolean second) {
        prob_settup();
        setAlien();
        while (xres == xdrone && yres == ydrone) {
            xres = dado6.roll();
            yres = dado6.roll();
        }
        terreno[xdrone][ydrone] = 1;//drone
        terreno[xres][yres] = 3;//tesouro
        setAlien();
        terreno[xalien][yalien]=2;
        setResorce();
        if(!second)
        {
            if(tipo==1)
            {
                minar=4;
            }
            else {
                minar=2;
            }
        }


    }
    public void move_Alien()
    {
        //System.out.println(xalien + " alien "+yalien);
        terreno[xalien][yalien]=0;
        //primeiro move se em x depois em y
        if(xalien<xdrone)
        {
            if(!(terreno[xalien+1][yalien]==1||terreno[xalien+1][yalien]==3||terreno[xalien+1][yalien]==4))
            {
                xalien=xalien+1;
                terreno[xalien][yalien]=2;
               //System.out.println(xalien + " alien "+yalien);
                return;
            }

        }
        if(xalien>xdrone)
        {
            if(!(terreno[xalien-1][yalien]==1||terreno[xalien-1][yalien]==3||terreno[xalien-1][yalien]==4)) {
                xalien = xalien - 1;
                terreno[xalien][yalien] = 2;
               // System.out.println(xalien + " alien " + yalien);
                return;
            }
        }
        if(yalien<ydrone)
        {
            if(!(terreno[xalien][yalien+1]==1||terreno[xalien][yalien+1]==3||terreno[xalien][yalien+1]==4))
            {
            yalien=yalien +1;
            terreno[xalien][yalien]=2;
            //System.out.println(xalien + " alien "+yalien);
            return;}
        }
        if(yalien>ydrone)
        {
            if(!(terreno[xalien][yalien-1]==1||terreno[xalien][yalien-1]==3)||terreno[xalien][yalien-1]==4) {
                yalien = yalien - 1;
                terreno[xalien][yalien] = 2;
               // System.out.println(xalien + " alien " + yalien);
                return;
            }
        }

    }
    public void setResorce()
    {
        int r= dado2.roll();
        switch (tipo) { //setting tesouro
            case 1:
                int aux= dado4.roll();
                if(aux==1)
                {
                    treasure="preto";
                }
                if(aux==2)
                {
                    treasure="verde";
                }
                if(aux==3)
                {
                    treasure="azul";
                }
                else
                {
                    treasure="artifact";
                }
                break;
            case 2:
                if(r==1)
                {
                    treasure="preto";
                } else {treasure="azul";}
                break;
            case 3:
                if(r==1)
                {
                    treasure="vermelho";
                } else {treasure="azul";}
                break;
            case 4:
                if(r==1)
                {
                    treasure="vermelho";
                } else {treasure="verde";}
                break;
        }
    }
    public boolean posso_minar()//determina se o planeta ainda tem recursos
    {
        if(minar>0)
        {
            minar --;
            //setResorce();
            return true;
        }
        else{
            //System.out.println("Os recursos do planeta estao esgotados.");
            return false;
        }
    }
}