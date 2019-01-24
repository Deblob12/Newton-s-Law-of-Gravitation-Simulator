public class Planet{
	public static final double G = 6.67E-11;  // Gravitational Constant
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p)
	{
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Returns distance between this planet and another inputted planet
	*
	* @param p This is the planet we want to find distance from
	* @return double This returns the distance between the planets
	*/
	public double calcDistance(Planet p)
	{
		return Math.sqrt((this.xxPos-p.xxPos) * (this.xxPos-p.xxPos) + 
			(this.yyPos-p.yyPos) * (this.yyPos-p.yyPos));
	}

	/** Returns total Force
	*
	* @param p This is the planet we want to find force exerted by
	* @return double This returns the force caused by the planet
	*/
	public double calcForceExertedBy(Planet p)
	{
		return (G * this.mass * p.mass / (calcDistance(p) * calcDistance(p)));
	}

	/** Returns force in X direction
	*
	* @param p This is the planet we want to find force exerted by
	* @return double This returns the force caused by the planet in the X direction
	*/
	public double calcForceExertedByX(Planet p)
	{
		return calcForceExertedBy(p) * ((p.xxPos - this.xxPos) / calcDistance(p));
	}

	/** Returns force in Y direction
	*
	* @param p This is the planet we want to find force exerted by
	* @return double This returns the force caused by the planet in the Y direction
	*/
	public double calcForceExertedByY(Planet p)
	{
		return calcForceExertedBy(p) * ((p.yyPos - this.yyPos) / calcDistance(p));
	}

	/** Returns force in X direction
	*
	* @param planets This is the all the planets we are calculating the netforce with
	* @return double This returns the net force caused by the planet in the X direction
	*/
	public double calcNetForceExertedByX(Planet[] planets)
	{
		double force = 0;
		for (Planet planet : planets)
		{
			if (this.equals(planet))
			{
				continue;
			}

			force += calcForceExertedByX(planet);
		}
		return force;
	}

	/** Returns force in Y direction
	*
	* @param planets This is the all the planets we are calculating the netforce with
	* @return double This returns the net force caused by the planet in the Y direction
	*/
	public double calcNetForceExertedByY(Planet[] planets)
	{
		double force = 0;
		for (Planet planet : planets)
		{
			if (this.equals(planet))
			{
				continue;
			}
			
			force += calcForceExertedByY(planet);
		}
		return force;
	}

	/** Updates position and velocity vectors
	*
	* @param time Time of the applied force
	* @param xxForce Force in Newtons in the X direction
	* @param yyForce Force in Newyons in the Y direction
	* @return void
	*/
	public void update(double time, double xxForce, double yyForce)
	{
		double xxAccel = xxForce / this.mass;
		double yyAccel = yyForce / this.mass;
		this.xxVel = this.xxVel + time * xxAccel;
		this.yyVel = this.yyVel + time * yyAccel;
		this.xxPos = this.xxPos + time * this.xxVel;
		this.yyPos = this.yyPos + time * this.yyVel;
	}

	/** Draw planet
	*
	* @param void
	* @return void
	*/
	public void draw()
	{
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}