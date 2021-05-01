package ui.graphic;


import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

public class Imagens{
    private static final Map<String, Image> imagens = new HashMap<>();

    static {

        imagens.put(ConstantesGui.FUNDO, new Image(Resources.getResourceFile(ConstantesGui.PATH_IMAGEM_FUNDO)));
        imagens.put(ConstantesGui.MILITAR, new Image(Resources.getResourceFile(ConstantesGui.PATH_MILITAR)));
        imagens.put(ConstantesGui.MINEIRO, new Image(Resources.getResourceFile(ConstantesGui.PATH_MINEIRO)));
        imagens.put(ConstantesGui.SPACE,new Image(Resources.getResourceFile(ConstantesGui.PATH_SPACE)));
        imagens.put(ConstantesGui.AZUL,new Image(Resources.getResourceFile(ConstantesGui.PATH_AZUL)));
        imagens.put(ConstantesGui.GREEN,new Image(Resources.getResourceFile(ConstantesGui.PATH_GREEN)));
        imagens.put(ConstantesGui.YELLOW,new Image(Resources.getResourceFile(ConstantesGui.PATH_YELLOW)));
        imagens.put(ConstantesGui.STATION,new Image(Resources.getResourceFile(ConstantesGui.PATH_STATION)));
        imagens.put(ConstantesGui.R_AZUL,new Image(Resources.getResourceFile(ConstantesGui.PATH_R_AZUL)));
        imagens.put(ConstantesGui.R_PRETO,new Image(Resources.getResourceFile(ConstantesGui.PATH_R_PRETO)));
        imagens.put(ConstantesGui.R_VERDE,new Image(Resources.getResourceFile(ConstantesGui.PATH_R_VERDE)));
        imagens.put(ConstantesGui.R_VERMELHO,new Image(Resources.getResourceFile(ConstantesGui.PATH_R_VERMELHO)));
        imagens.put(ConstantesGui.PLANET,new Image(Resources.getResourceFile(ConstantesGui.PATH_PLANET)));
        imagens.put(ConstantesGui.UP,new Image(Resources.getResourceFile(ConstantesGui.PATH_UP)));
        imagens.put(ConstantesGui.DOWN,new Image(Resources.getResourceFile(ConstantesGui.PATH_DOWN)));
        imagens.put(ConstantesGui.RIGHT,new Image(Resources.getResourceFile(ConstantesGui.PATH_RIGHT)));
        imagens.put(ConstantesGui.LEFT,new Image(Resources.getResourceFile(ConstantesGui.PATH_LEFT)));
        imagens.put(ConstantesGui.ALIEN,new Image(Resources.getResourceFile(ConstantesGui.PATH_ALIEN)));
        imagens.put(ConstantesGui.SHIP,new Image(Resources.getResourceFile(ConstantesGui.PATH_SHIP)));
        imagens.put(ConstantesGui.DRONE,new Image(Resources.getResourceFile(ConstantesGui.PATH_DRONE)));
        imagens.put(ConstantesGui.GAMEOVER,new Image(Resources.getResourceFile(ConstantesGui.PATH_GAMEOVER)));
        imagens.put(ConstantesGui.WIN,new Image(Resources.getResourceFile(ConstantesGui.PATH_WIN)));
        //imagens.put(ConstantesGui.MONITOR,new Image(Resources.getResourceFile(ConstantesGui.PATH_MONITOR)));
    }

    public static Image getImagem(String nome) {
        return imagens.get(nome);
    }

}
