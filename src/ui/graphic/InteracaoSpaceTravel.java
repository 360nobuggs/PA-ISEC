package ui.graphic;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import states.InteracaoEsperada;
import states.Maquina_observavel;

public class InteracaoSpaceTravel extends HBox {
    private Maquina_observavel modeloObs;
    public InteracaoSpaceTravel(Maquina_observavel modelo) {
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

        //mostra os recursos do planeta
        Button continuar = new Button("Continuar viagem");
        Button parar = new Button("Parar no planeta");
        setAlignment(Pos.CENTER);
        setSpacing(80);
        setPadding(new Insets(5, 5, 5, 5));
        //estetica

        parar.setMinSize(200,80);
        BackgroundSize backgroundSize = new BackgroundSize(200, 80, false, false, false, false);
        BackgroundImage myBI2= new BackgroundImage(Imagens.getImagem(ConstantesGui.YELLOW),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        parar.setBackground(new Background(myBI2));
        parar.setMinSize(200,80);
        parar.setStyle("-fx-text-fill: black;-fx-font-size: 18");

        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.GREEN),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        continuar.setBackground(new Background(myBI));
        continuar.setStyle("-fx-text-fill: black;-fx-font-size: 18");
        continuar.setMinSize(200,80);

        if(modeloObs.pode_converter())//Necessita de ter o sexto tripulante para converter
        {
            Button converter= new Button("Converter e continuar");
            converter.setMinSize(200,80);
            BackgroundImage myBI3= new BackgroundImage(Imagens.getImagem(ConstantesGui.AZUL),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    backgroundSize);

            converter.setBackground(new Background(myBI3));
            converter.setStyle("-fx-text-fill: black;-fx-font-size: 18");
            getChildren().addAll(continuar,parar,converter);
            converter.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    items_conv();
                }
            });
        }
        else{
            getChildren().addAll(continuar, parar);
        }

        continuar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){

                    modeloObs.space_Exploration();
            }

        });
        parar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.planet_ex();
            }
        });
    }
    private void atualizaVista(){
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        setVisible(interacaoEsperada == InteracaoEsperada.INTERACAO_SPACE_EX );
    }

    private void items_conv()
    {
        getChildren().clear();
        Button escudo = new Button("Escudo(preto,verde,azul)");
        Button ammo = new Button("Ammo (preto, azul)");
        Button combustivel = new Button("Combustivel (azul,vermelho,verde)");
        Button sair = new Button("Sair");
        escudo.setMinSize(201,95);
        ammo.setMinSize(201,95);
        combustivel.setMinSize(201,95);
        sair.setMinSize(201,95);
        setSpacing(30);

        escudo.setStyle("-fx-text-fill: white");
        sair.setStyle("-fx-text-fill: #0099FF;-fx-font-size: 20");
        ammo.setStyle("-fx-text-fill: white");
        combustivel.setStyle("-fx-text-fill: white");
        BackgroundSize backgroundSize = new BackgroundSize(201, 95, false, false, false, false);
        BackgroundImage myBI3= new BackgroundImage(Imagens.getImagem(ConstantesGui.AZUL),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        BackgroundImage myBI2= new BackgroundImage(Imagens.getImagem(ConstantesGui.GREEN),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);

        escudo.setBackground(new Background(myBI3));
        ammo.setBackground(new Background(myBI3));
        combustivel.setBackground(new Background(myBI3));
        sair.setBackground(new Background(myBI2));

        getChildren().addAll(escudo,ammo,combustivel,sair);
        escudo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
               modeloObs.conv_space(1);
            }

        });
        ammo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.conv_space(2);
            }

        });
        combustivel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.conv_space(3);
            }

        });
        sair.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.space_Exploration();
                getChildren().clear();
                organizaComponentes();
            }

        });
    }
}
