package ui.graphic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import states.InteracaoEsperada;
import states.Maquina_observavel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Root extends BorderPane{

    private Maquina_observavel modeloObs;// ligacao ao modeloObs

    private VistaDados vistaDados;

    public Root(Maquina_observavel modelo) {
        this.modeloObs = modelo;
        this.modeloObs.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       atualizaVista();
                    }


                }
        );
        organizaComponentes();
    }


    private void organizaComponentes(){


        BackgroundSize backgroundSize = new BackgroundSize(960, 500, false, false, false, false);
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        setBackground(new Background(myBI));

        InteracaoPickShip interacaoPickShip = new InteracaoPickShip(modeloObs);
        InteracaoSpaceTravel interacaoSpaceEx = new InteracaoSpaceTravel(modeloObs);
        InteracaoSpaceStation interacaoSpaceStation= new InteracaoSpaceStation(modeloObs);
        InteracaoPlanetEx interacaoPlanetEx = new InteracaoPlanetEx(modeloObs);
        InteracaoGameOver interacaoGameOver= new InteracaoGameOver(modeloObs);
        InteracaoWinning interacaoWinning=new InteracaoWinning(modeloObs);
        InteracaoSveLoad interacaoSveLoad= new InteracaoSveLoad(modeloObs);

        VistaDados vistaDados = new VistaDados(modeloObs);
        StackPane bottom = new StackPane(interacaoPickShip, interacaoSpaceEx);// StackPane ?
        StackPane right= new StackPane(interacaoSpaceStation,interacaoPlanetEx,interacaoGameOver,interacaoWinning);
        bottom.setBorder(new Border(new BorderStroke(Color.CADETBLUE, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        right.setMinHeight(0);
        //setPadding(new Insets(10, 20, 10, 20));
        bottom.setMinHeight(90);
        this.setTop(vistaDados);
        this.setBottom(bottom);
        this.setCenter(right);
        this.setRight(interacaoSveLoad);
    }

    private void atualizaVista(){//ALTERA A SCENE CONFORME O ESTADO
        //VISTO QUE BORDERPANE É DINÂMICO É NECESSÁRIO REMOVER E ADICIONAR VALORES PARA NAO EMPURRAREM A GUI
        //novo background
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        BackgroundSize backgroundSize = new BackgroundSize(950, 500, false, false, false, false);
        String nome_imagem=ConstantesGui.SPACE;
        if(interacaoEsperada==InteracaoEsperada.INTERACAO_PICKSHIP)
        {
            nome_imagem=ConstantesGui.FUNDO;
            organizaComponentes();
        }
        if(interacaoEsperada==InteracaoEsperada.INTERACAO_SPACE_EX) {
            nome_imagem=ConstantesGui.SPACE;
            organizaComponentes();
            //centerProperty().setValue(null);
        }
        else if(interacaoEsperada==InteracaoEsperada.INTERACAO_SPACE_STATION) {
            nome_imagem=ConstantesGui.STATION;
            backgroundSize = new BackgroundSize(970, 570, false, false, false, false);
            //organizaComponentes();
            bottomProperty().setValue(null);
            rightProperty().setValue(null);
            VBox menu_d = new VBox();
            Label menu= new Label();
            menu.setText("Cargo Upgrade: 3 de cada recurso"+'\n'+"Shield energy: 1 de cada recurso"+'\n'+"Recrutar Aliens: 1 de cada recurso"+'\n'+"Novo Drone: 3 de cada recurso");
            menu_d.getChildren().add(menu);
            menu_d.setAlignment(Pos.BOTTOM_RIGHT);
            menu.setStyle("-fx-font-size: 12;-fx-text-fill: #c4d8de;-fx-background-color: rgba(135,206,250, 0.2);");
            menu.setPadding(new Insets(10,10,10,10));
            bottomProperty().setValue(menu);
        }
        else if(interacaoEsperada==InteracaoEsperada.INTERACAO_PLANET_EX) {
            nome_imagem=ConstantesGui.PLANET;
            backgroundSize= new BackgroundSize(970, 584, false, false, false, false);
            bottomProperty().setValue(null);
            rightProperty().setValue(null);
        }
        else if(interacaoEsperada==InteracaoEsperada.INTERACAO_GAMEOVER) {
            nome_imagem=ConstantesGui.GAMEOVER;
            backgroundSize= new BackgroundSize(970, 584, false, false, false, false);
            bottomProperty().setValue(null);
            topProperty().setValue(null);
            rightProperty().setValue(null);
        }
        else if(interacaoEsperada==InteracaoEsperada.INTERACAO_WINNING) {
            nome_imagem=ConstantesGui.WIN;
            backgroundSize= new BackgroundSize(970, 584, false, false, false, false);
            bottomProperty().setValue(null);
            topProperty().setValue(null);
            rightProperty().setValue(null);
        }

            BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(nome_imagem),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    backgroundSize);
            setBackground(new Background(myBI));
    }
}
