import Layout.Side;
import Moving.Movements;
import Randomization.Randomize;

public class Main {
    public static void main(String[] args) {
        // Side initialization
        Side index = new Side();
        Movements moving = new Movements();
        Randomize random = new Randomize();

        // Randomization Process
        random.start(index,moving);
    }
}
