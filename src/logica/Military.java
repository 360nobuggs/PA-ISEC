package logica;

public class Military extends Ship {
    public Military()
    {
        super();
        this.fuel=35;
        this.shield=8;
        this.balas=18;
        this.id=true;
    }
    @Override
    public void upgrade()
    {

        if (upgrade<2) {
            upgrade++;
            cargo_limit= cargo_limit+6;
        }

    }
    @Override
    public void shield_up()
    {
        shield=8;

    }
}
