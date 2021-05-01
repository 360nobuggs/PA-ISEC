package ui.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import states.InteracaoEsperada;
import states.Maquina_observavel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InteracaoGameOver extends VBox{
    private Maquina_observavel modeloObs;

    public InteracaoGameOver(Maquina_observavel modelo) {
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

        atualizaVista();
    }

    private void organizaComponentes() {
        Label l= new Label();
        l.setText("GAME OVER");
        l.setStyle("-fx-font-size: 20;-fx-text-fill: white ;");
        setSpacing(20);
        Button novo_jogo = new Button("Começar jogo novo.");
        Button exit= new Button("Terminar jogo.");
        novo_jogo.setStyle("-fx-text-fill: black;-fx-font-size: 20");
        exit.setStyle("-fx-text-fill: black;-fx-font-size: 20");
        BackgroundSize backgroundSize = new BackgroundSize(270, 95, false, false, false, false);
        BackgroundImage myBI3= new BackgroundImage(Imagens.getImagem(ConstantesGui.GREEN),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        novo_jogo.setMinSize(270,95);
        exit.setMinSize(270,95);
        novo_jogo.setBackground(new Background(myBI3));
        exit.setBackground(new Background(myBI3));
        novo_jogo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.novo_jogo();
            }
        });
        setAlignment(Pos.CENTER);
        exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                System.exit(0);
            }
        });
        setAlignment(Pos.CENTER);
        getChildren().addAll(l,novo_jogo,exit);
    }


    private void atualizaVista(){
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        setVisible(interacaoEsperada == InteracaoEsperada.INTERACAO_GAMEOVER );
    }
}

