public class TestPlanet{
	public static void main(String[] args){
		Planet p1 = new Planet(1, 0, 0, 0, 10, "none");
		Planet p2 = new Planet(3, 3, 0, 0, 5, "none");

		StdOut.println("Force Exerted in the X direction: " + p1.calcForceExertedByX(p2));
		StdOut.println("Force Exerted in the Y direction: " + p1.calcForceExertedByY(p2));
	}
}