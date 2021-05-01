import  data.*;
import ui.text.Text_Interface;

public class Main {
    public static void main(String[] args) {
        Text_Interface starter = new Text_Interface(new StateMachineGame());
        starter.run();

        //new GameFrame(new GameState());
    }
}
