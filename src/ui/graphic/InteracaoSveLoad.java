package ui.graphic;

import data.StateMachineGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import states.InteracaoEsperada;
import states.Maquina_observavel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class InteracaoSveLoad extends VBox {

    private Maquina_observavel modeloObs;
    public InteracaoSveLoad(Maquina_observavel modelo) {
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
        setSpacing(5);
        Button save_jogo = new Button("Guardar jogo");
        Button load_jogo = new Button("Carregar jogo");


        BackgroundSize backgroundSize = new BackgroundSize(201, 95, false, false, false, false);
        BackgroundImage myBI3= new BackgroundImage(Imagens.getImagem(ConstantesGui.YELLOW),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                backgroundSize);
        save_jogo.setBackground(new Background(myBI3));
        save_jogo.setMinSize(100,30);
        load_jogo.setMinSize(100,30);
        load_jogo.setBackground(new Background(myBI3));
        save_jogo.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                FileChooser fileChooser = new FileChooser();


                File selectedFile = fileChooser.showSaveDialog(null);
                if (selectedFile != null) {
                    try{
                        modeloObs.saveGameToFile(selectedFile, modeloObs.getMaqEstados());
                    }catch(IOException ex){
                        Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                        dialogoResultado.setHeaderText("Save");
                        dialogoResultado.setContentText("Operation failed: " + ex);
                        System.out.println(ex);
                        dialogoResultado.showAndWait();
                    }
                } else {
                    System.out.println("Operation canceled ");
                }
            }
        });
        load_jogo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                    FileChooser fileChooser = new FileChooser();
                    File selectedFile = fileChooser.showOpenDialog(null);
                    if (selectedFile != null) {
                        try{
                            StateMachineGame model =
                                    (StateMachineGame) modeloObs.retrieveGameFromFile(selectedFile);
                            if(model != null){
                                modeloObs.setMaqEstados(model);
                            }
                        }catch(IOException | ClassNotFoundException ex){
                            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                            dialogoResultado.setHeaderText("Load");
                            dialogoResultado.setContentText("Operation failed: " + ex);
                            dialogoResultado.showAndWait();
                        }

                    } else {
                        System.out.println("Operation canceled ");
                    }
            }
        });
        getChildren().addAll(save_jogo,load_jogo);
    }

    private void atualizaVista(){
        InteracaoEsperada interacaoEsperada = modeloObs.getInteracaoEsperada();
        setVisible(interacaoEsperada == InteracaoEsperada.INTERACAO_SPACE_EX );
    }

}
