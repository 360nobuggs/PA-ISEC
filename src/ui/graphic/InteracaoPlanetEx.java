package ui.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import states.InteracaoEsperada;
import states.Maquina_observavel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class InteracaoPlanetEx  extends VBox {
    private Maquina_observavel modeloObs;
    public InteracaoPlanetEx(Maquina_observavel modelo) {
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
        Button up = new Button("");
        Button down = new Button("");
        Button right = new Button("");
        Button left = new Button("");
        Button continua = new Button("Explorar novamente.");
        Button sair = new Button("Voltar para orbita.");
        continua.setStyle("-fx-text-fill: #c4d8de;-fx-font-size:14;-fx-background-color: rgba(79,121,66, 0.8);");
        sair.setStyle("-fx-text-fill: #c4d8de;-fx-font-size:14;-fx-background-color: rgba(79,121,66, 0.8);");
        continua.setMinSize(110,50);
        sair.setMinSize(110,50);
        BackgroundSize backgroundSize = new BackgroundSize(45, 45, false, false, false, false);
        up.setMinSize(45,45);
        down.setMinSize(45,45);
        right.setMinSize(45,45);
        left.setMinSize(45,45);
        BackgroundImage i_up= new BackgroundImage(Imagens.getImagem(ConstantesGui.UP),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);

        up.setBackground(new Background(i_up));
        down.setBackground(new Background(i_up));
        right.setBackground(new Background(i_up));
        left.setBackground(new Background(i_up));
        up.setRotate(-90);
        left.setRotate(-180);
        down.setRotate(90);
        up.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){

                modeloObs.movimento('l');
            }
        });
        right.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){

                modeloObs.movimento('d');
            }
        });
        left.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.movimento('u');
            }
        });
        down.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){

                modeloObs.movimento('r');
            }
        });
        continua.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.add_res();
                modeloObs.explorar_novamente();
            }
        });
        sair.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.add_res();
                modeloObs.space_Exploration();
            }
        });
        HBox box= new HBox();
        box.setPadding(new Insets(0,0,10,0));
        up.setPadding(new Insets(20,0,0,0));
        box.setAlignment(Pos.BOTTOM_CENTER);
        setAlignment(Pos.BOTTOM_CENTER);
        //GRIDPANE

        box.getChildren().addAll(left,down,right);
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        if(interacaoEsperada==InteracaoEsperada.INTERACAO_PLANET_EX)
        {
            if(!modeloObs.termina_exploracao())
            {
                getChildren().clear();
                getChildren().addAll(mapa(),up,box);
            }
            else
            {
                setAlignment(Pos.TOP_CENTER);
                HBox box2=new HBox();
                box2.getChildren().addAll(continua,sair);
                box2.setPadding(new Insets(50,0,0,0));
                box2.setSpacing(100);
                box2.setAlignment(Pos.TOP_CENTER);
                getChildren().addAll(box2);
            }
        }

    }
    private void atualizaVista(){
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        setVisible(interacaoEsperada == InteracaoEsperada.INTERACAO_PLANET_EX );
        getChildren().clear();
        organizaComponentes();
    }
    GridPane mapa()
    {
        GridPane mapa= new GridPane();
        BackgroundSize backgroundSize = new BackgroundSize(50, 50, false, false, false, false);
        BackgroundImage i_ship= new BackgroundImage(Imagens.getImagem(ConstantesGui.SHIP),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage i_drone= new BackgroundImage(Imagens.getImagem(ConstantesGui.DRONE),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage i_tesouro= new BackgroundImage(Imagens.getImagem(ConstantesGui.R_VERDE),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage i_alien= new BackgroundImage(Imagens.getImagem(ConstantesGui.ALIEN),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        Button ship= new Button();
        ship.setMinSize(50,50);
        Button drone= new Button();
        drone.setMinSize(50,50);
        Button alien= new Button();
        alien.setMinSize(50,50);
        Button tesouro= new Button();
        tesouro.setMinSize(50,50);
        //backgrounds
        ship.setBackground(new Background(i_ship));
        tesouro.setBackground(new Background(i_tesouro));
        alien.setBackground(new Background(i_alien));
        drone.setBackground(new Background(i_drone));

        mapa.setAlignment(Pos.CENTER);
        mapa.setPadding(new Insets(0,0,0,0));

        //constraints para dar o mesmo tamanho a todas as células
        RowConstraints row= new RowConstraints();
        ColumnConstraints col= new ColumnConstraints();
        mapa.setStyle("-fx-font-size: 14;-fx-text-fill: #c4d8de;-fx-background-color: rgba(176,196,222, 0.4);");
        mapa.setGridLinesVisible(true);
        row.setMinHeight(50);
        col.setMinWidth(50);;
        mapa.setMaxHeight(300);
        mapa.setMaxWidth(300);
        for(int i=0;i<6;i++)
        {
            mapa.getRowConstraints().add(row);
            mapa.getColumnConstraints().add(col);
        }
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        if(interacaoEsperada==InteracaoEsperada.INTERACAO_PLANET_EX) //Para mapear é necessário que o planeta esteja corretamente inicializado.
        {
            ArrayList<int[]> lista= modeloObs.get_pos();
            if(!modeloObs.check_tresure())
            {
                int a_tesouro[]= lista.get(0);
                mapa.add(tesouro,a_tesouro[0],a_tesouro[1]);
            }
            int a_ship[]= lista.get(1);
            int a_drone[]= lista.get(2);
            int a_alien[]= lista.get(3);
            mapa.add(ship,a_ship[0],a_ship[1]);
            mapa.add(alien,a_alien[0],a_alien[1]);
            mapa.add(drone,a_drone[0],a_drone[1]);
        }





        return mapa;
    }
}
