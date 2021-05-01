package ui.graphic;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import states.InteracaoEsperada;
import states.Maquina_observavel;


public class InteracaoSpaceStation  extends VBox {
    private Maquina_observavel modeloObs;
    String def_Style="-fx-font-size:14;-fx-background-color: rgba(135,206,250, 0.8);";
    public InteracaoSpaceStation(Maquina_observavel modelo) {
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
    private void organizaComponentes(){
        modeloObs.setOld_value(9);
        getChildren().clear();
        Button converter = new Button("Conversão de Recursos");
        Button upgrade_cargo = new Button("Upgrade de Cargo");
        Button refill= new Button("Recarregar Escudo");
        Button buyDrone= new Button("Comparar Drone");
        Button buyTrip= new Button("Recrutar tripulação");
        Button sair= new Button("Sair");


        setAlignment(Pos.CENTER_RIGHT);
        Border b =new Border((new BorderStroke(Color.STEELBLUE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0.2))));

        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(5, 5, 5, 5));

        converter.setStyle(def_Style);
        converter.setMinSize(170,50);
        converter.setBorder(b);

        upgrade_cargo.setStyle(def_Style);
        upgrade_cargo.setMinSize(170,50);
        upgrade_cargo.setBorder(b);

        refill.setStyle(def_Style);
        refill.setMinSize(170,50);
        refill.setBorder(b);

        sair.setStyle(def_Style);
        sair.setMinSize(170,50);
        sair.setBorder(b);

        buyDrone.setStyle(def_Style);
        buyDrone.setMinSize(170,50);
        buyDrone.setBorder(b);
        buyTrip.setStyle(def_Style);
        buyTrip.setMinSize(170,50);
        buyTrip.setBorder(b);


        if(modeloObs.tem_drone()||modeloObs.tripulacao_cheia())//Só mostra certos butões se o navio tem em falta algum dos items em venda
        {
            getChildren().addAll(converter, refill,upgrade_cargo,sair);
        }
        else if(!modeloObs.tem_drone()||modeloObs.tripulacao_cheia())
        {
            getChildren().addAll(converter, refill,upgrade_cargo,buyDrone,sair);
        }
        else if(modeloObs.tem_drone()||!modeloObs.tripulacao_cheia())
        {
            getChildren().addAll(converter, refill,upgrade_cargo,buyTrip,sair);
        }
        else
        {
            getChildren().addAll(converter, refill,upgrade_cargo,buyTrip,buyDrone,sair);
        }

        buyDrone.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.buy_drone();
            }
        });

        buyTrip.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
               modeloObs.buy_trip();
            }
        });

        converter.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
              conversao_gui();
            }
        });
        refill.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
               modeloObs.refill();
            }
        });
        upgrade_cargo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.upgrade_cargo();
                getChildren().remove(upgrade_cargo);
            }
        });
        sair.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.space_Exploration();
            }
        });
    }

    private void atualizaVista(){
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        setVisible(interacaoEsperada == InteracaoEsperada.INTERACAO_SPACE_STATION );
    }
    private void conversao_gui()
    {
        Button azul = new Button("");
        Button verde = new Button("");
        Button vermelho= new Button("");
        Button preto= new Button("");
        azul.setMinSize(100,100);
        verde.setMinSize(100,100);
        vermelho.setMinSize(100,100);
        preto.setMinSize(100,100);
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, false, false, false);
        BackgroundImage b_azul= new BackgroundImage(Imagens.getImagem(ConstantesGui.R_AZUL),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage b_preto= new BackgroundImage(Imagens.getImagem(ConstantesGui.R_PRETO),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage b_vermelho= new BackgroundImage(Imagens.getImagem(ConstantesGui.R_VERMELHO),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage b_verde= new BackgroundImage(Imagens.getImagem(ConstantesGui.R_VERDE),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        azul.setBackground(new Background(b_azul));
        preto.setBackground(new Background(b_preto));
        vermelho.setBackground(new Background(b_vermelho));
        verde.setBackground(new Background(b_verde));
        HBox one= new HBox();
        HBox two= new HBox();
        one.setSpacing(7);
        two.setSpacing(7);
        one.setAlignment(Pos.CENTER);
        two.setAlignment(Pos.CENTER);

        //SLIDER
        Slider slider = new Slider();
        slider.setMaxWidth(100);
        slider.setStyle("-fx-control-inner-background:steelblue;-fx-tick-label-fill: rgb(255,0,255);");
        slider.setMin(1);
        slider.setMax(5);
        slider.setValue(1);
        slider.setShowTickLabels(true);
        slider.setBlockIncrement(1);
        slider.adjustValue(1);

        slider.valueProperty().addListener((obs, oldval, newVal) ->
                slider.setValue(newVal.intValue()));


        azul.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(modeloObs.get_old_value()==9)
                {
                    modeloObs.setOld_value(0);
                }
                else
                {
                    modeloObs.conversao(modeloObs.get_old_value(),0,(int)slider.getValue());
                    organizaComponentes();
                }
            }
        });
        preto.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(modeloObs.get_old_value()==9)
                {
                    modeloObs.setOld_value(1);
                }
                else
                {
                    modeloObs.conversao(modeloObs.get_old_value(),1,(int)slider.getValue());
                    organizaComponentes();
                }
            }
        });
        vermelho.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(modeloObs.get_old_value()==9)
                {
                    modeloObs.setOld_value(2);
                }
                else
                {
                    modeloObs.conversao(modeloObs.get_old_value(),2,(int)slider.getValue());
                    organizaComponentes();
                }
            }
        });
        verde.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(modeloObs.get_old_value()==9)
                {
                    modeloObs.setOld_value(3);
                }
                else
                {
                    modeloObs.conversao(modeloObs.get_old_value(),3,(int)slider.getValue());
                    organizaComponentes();
                }
            }
        });
        one.getChildren().addAll(verde,vermelho);
        two.getChildren().addAll(azul,preto);
        getChildren().clear();
        getChildren().addAll(one,two,slider);

    }

}


