

import javafx.scene.Group;
import javafx.scene.media.*;
import data.StateMachineGame;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import states.*;
import ui.graphic.Root;

public class App_graphic extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        playMusic();
        StateMachineGame MaqEstados = new StateMachineGame();
        Maquina_observavel Observavel =
                new Maquina_observavel(MaqEstados);
        Root r= new Root(Observavel);
        Scene scene = new Scene(new Root(Observavel),950,500);
        primaryStage.getIcons().add(new Image(App_graphic.class.getResourceAsStream("ui/graphic/images/logo_2.jpg")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Space Odyssey");
        primaryStage.show();
    }
    private static MediaPlayer mediaPlayer;

    public static void playMusic()
    {
        Media pick = new Media(App_graphic.class.getResource("ui/graphic/audio/audio.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(pick);
        mediaPlayer.setVolume(0.9);
        mediaPlayer.setAutoPlay(true);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
