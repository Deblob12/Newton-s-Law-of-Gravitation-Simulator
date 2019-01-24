public class NBody{
	/** Find Universe Radius from input file
	*
	*   @param filename file containing data on universe
	*   @return double radius of the universe
	*/
	public static double readRadius(String fileName)
	{
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}

	/** Read all planets in file into a Planet Array
	*
	*   @param filename file containing data on universe
	*   @return Planets[] array of all planets in file
	*/
	public static Planet[] readPlanets(String fileName)
	{
		In in = new In(fileName);
		int numPlanets = in.readInt();
		Planet[] planets = new Planet[numPlanets];
		in.readDouble();
		for (int i = 0; i < numPlanets; i++)
		{
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return planets;
	}

	public static void main(String[] args)
	{
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		drawBackground(radius);
		drawPlanets(planets);
		StdDraw.enableDoubleBuffering();
		double time;

		for (time = 0; time < T; time += dt)
		{
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i = 0; i < planets.length; i++)
			{
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++)
				{
					planets[i].update(dt, xForces[i], yForces[i]);
				}
			drawBackground(radius);
			drawPlanets(planets);
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

	/** Draws the background image and scale appropriately
	*
	*   @param radius Radius of universe, used to scale
	*   @return void
	*/
	public static void drawBackground(double radius)
	{
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
	}

	/** Draws the background planets on the background
	*
	*   @param planets Planet[] containing all planets in this universe
	*   @return void
	*/
	public static void drawPlanets(Planet[] planets)
	{
		for (Planet planet : planets)
		{
			planet.draw();
		}
	}
}
