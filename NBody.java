import java.util.Arrays;

public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        Body[] bodies = new Body[in.readInt()];
        in.readDouble();

        for (int i = 0; i < bodies.length; i++) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        Body[] bodies = readBodies(filename);

        StdDraw.setScale(-readRadius(filename), readRadius(filename));
        StdDraw.clear();
        StdDraw.picture("images/starfield.jpg");
    }
}