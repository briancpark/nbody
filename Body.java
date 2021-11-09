public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;

    public String imgFileName;

    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Body p) {
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Body p) {
        return G * this.mass *  p.mass / (Math.pow(calcDistance(p), 2));
    }

    public double calcForceExertedByX(Body p) {
        return (calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / calcDistance(p);
    }

    public double calcForceExertedByY(Body p) {
        return (calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Body[] allP) {
        double netForce = 0;

        for (Body p : allP) {
            if (!this.equals(p)) {
               netForce = netForce + this.calcForceExertedByX(p);
            }
        }

        return netForce;
    }

    public double calcNetForceExertedByY(Body[] allP) {
        double netForce = 0;

        for (Body p : allP) {
            if (!this.equals(p)) {
                netForce = netForce + this.calcForceExertedByY(p);
            }
        }

        return netForce;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
}