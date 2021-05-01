package ui.graphic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import states.InteracaoEsperada;
import states.Maquina_observavel;


public class VistaDados extends VBox {

    private Maquina_observavel modeloObs;// ligacao ao modeloObs

    private Label labelDados;

    public VistaDados(Maquina_observavel modelo) {
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

        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        String novo_jogo="";
        if(interacaoEsperada==InteracaoEsperada.INTERACAO_PICKSHIP) {
            novo_jogo = "Quando encarada por extinção certa, a raça humana escolhe uma equipa de renegados para encontrar o novo mundo." + '\n' +
                    "Bem-vindo Capitao, qual dos navios deseja, militar ou mineiro? ";
        }
            labelDados = new Label("");
            getChildren().addAll(labelDados);
            labelDados.setTextFill(Color.web("#0076a4"));
            labelDados.setStyle("-fx-font-size: 14;-fx-text-fill: #c4d8de;");
            setSpacing(5);
            setPadding(new Insets(10, 10, 10, 10));
            setAlignment(Pos.TOP_LEFT);

            AnimateText(labelDados, novo_jogo);
            atualizaVista();
    }
    private void AnimateText(Label lbl, String descImp) {
        String content = descImp;
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(4050));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));
            }
        };

        animation.play();

    }
    private void atualizaVista(){
        if(modeloObs.getInteracaoEsperada() != InteracaoEsperada.INTERACAO_PICKSHIP) {
            String aux = modeloObs.MsgLog();
            AnimateText(labelDados, aux);
            labelDados.setPadding(new Insets(2, 12, 2, 10));
            Border b =new Border((new BorderStroke(Color.DEEPSKYBLUE, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(2))));
            labelDados.setBorder(b);


            if(modeloObs.getInteracaoEsperada() == InteracaoEsperada.INTERACAO_SPACE_EX||modeloObs.getInteracaoEsperada() == InteracaoEsperada.INTERACAO_SPACE_STATION) {
                labelDados.setStyle("-fx-font-size: 14;-fx-text-fill: #c4d8de;-fx-background-color: rgba(135,206,250, 0.5);");
                setSpacing(5);
                setPadding(new Insets(10, 10, 10, 10));
                Border c =new Border((new BorderStroke(Color.DEEPSKYBLUE, BorderStrokeStyle.NONE, new CornerRadii(0), new BorderWidths(2))));
                setAlignment(Pos.TOP_LEFT);
                labelDados.setBorder(c);
            }
            if(modeloObs.getInteracaoEsperada()==InteracaoEsperada.INTERACAO_PLANET_EX)
            {
                labelDados.setStyle("-fx-font-size: 14;-fx-text-fill: #c4d8de;-fx-background-color: rgba(79,121,66, 0.4);");
                setSpacing(5);
                setPadding(new Insets(10, 10, 10, 10));
                Border c =new Border((new BorderStroke(Color.CYAN, BorderStrokeStyle.NONE, new CornerRadii(0), new BorderWidths(2))));
                setAlignment(Pos.TOP_LEFT);
                labelDados.setBorder(c);
            }
        }
    }
}
