package cards;

public class Mushroom extends EdibleItem{
    protected int sticksPerMushroom;
    public Mushroom(CardType type, String cardName) {
        super(type, cardName);
        switch (cardName) {
            case "honeyfungus":
                sticksPerMushroom = 1;
                break;
            case "treeear":
                sticksPerMushroom = 2;
                break;
            case "lawyerswig":
                sticksPerMushroom = 1;
                break;
            case "shiitake":
                sticksPerMushroom = 2;
                break;
            case "henofwoods":
                sticksPerMushroom = 1;
                break;
            case "birchbolete":
                sticksPerMushroom = 2;
                break;
            case "porcini":
                sticksPerMushroom = 3;
                break;
            case "chanterelle":
                sticksPerMushroom = 2;
                break;
            case "morel":
                sticksPerMushroom = 4;
                break;
        }
    }
    public int getSticksPerMushroom() {
        return sticksPerMushroom;
    }
}
