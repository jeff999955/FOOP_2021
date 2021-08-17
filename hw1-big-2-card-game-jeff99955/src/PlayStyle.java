public enum PlayStyle {
    single("single"),
    pair("pair"),
    straight("straight"),
    full_house("full house"),
    invalid("invalid"),
    pass("pass");

    private String name;

    PlayStyle(String n){
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }
}