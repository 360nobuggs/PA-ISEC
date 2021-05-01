package logica;

public class Mining extends Ship {
    public Mining()
    {
        super();
        this.fuel=53;
        this.shield=16;
        this.balas=8;
        this.cargo=cargo;
    }
    @Override
    public void upgrade()
    {
        if (upgrade<4) {
            upgrade++;
            cargo_limit= cargo_limit+6;
        }

    }
    @Override
    public void shield_up()
    {
        shield=16;

    }
}
