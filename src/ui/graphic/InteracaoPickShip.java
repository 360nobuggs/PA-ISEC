package ui.graphic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import states.*;


public class InteracaoPickShip extends HBox{
    private Maquina_observavel modeloObs;
    public InteracaoPickShip(Maquina_observavel modelo)
    {
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
        Button militar = new Button("");
        Button mineiro = new Button("");

        //militar.setStyle("-fx-background-image: url('/images/militar.jpg')");
        //Image image = new Image(getClass().getResourceAsStream(ConstantesGui.PATH_MILITAR));
        //style militar
        setAlignment(Pos.CENTER);
        setSpacing(80);
        setPadding(new Insets(5, 5, 5, 5));

        ImageView mili =new ImageView(Imagens.getImagem(ConstantesGui.MILITAR));
        mili.setFitHeight(180);
        mili.setFitWidth(300);
        militar.setGraphic(mili);
        militar.setStyle("-fx-background-color: transparent;");
        //style mineiro



        ImageView min =new ImageView(Imagens.getImagem(ConstantesGui.MINEIRO));
        min.setFitHeight(180);
        min.setFitWidth(300);
        mineiro.setGraphic(min);
        mineiro.setStyle("-fx-radius: 50;-fx-background-color: transparent;");

        getChildren().addAll(militar, mineiro);

        militar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.pickship(1);
            }
        });
        mineiro.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                modeloObs.pickship(2);
            }
        });

    }

    private void atualizaVista(){
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        setVisible(interacaoEsperada == InteracaoEsperada.INTERACAO_PICKSHIP );
    }

}
