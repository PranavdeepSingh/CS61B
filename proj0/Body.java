public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public double netForceX ;
	public double netForceY ;
	public String imgToDraw = new String();

	public Body(double xP, double yP, double xV,double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;     //copy values passed .
		yyVel = yV;
		mass  = m ;
		imgFileName=img;

	}

	public Body(Body b){ //copy the values of  object  passed into a new object.
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass  = b.mass ;
		imgFileName=b.imgFileName;
	}


	Body(){} //default constuctor.

	public double calcDistance(Body b){ // to calculate distance between two objects.
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcForceExertedBy(Body b){ //to calculate force exerted by one body on another.
		double r = this.calcDistance(b);
		double force = (6.67E-11*this.mass*b.mass)/(r*r);
	return force;
	}
	
	public double calcForceExertedByX(Body b){ // to calculate x-component of force exerted on body.
		double force = this.calcForceExertedBy(b);
		double dx    = b.xxPos - this.xxPos;
		double r = this.calcDistance(b);

		double fx    = (force*dx)/r;
		return fx;
	} 

	public double calcForceExertedByY(Body b){ // to calculate y-component of force exerted on body.
		double force = this.calcForceExertedBy(b);
		double dy    = b.yyPos - this.yyPos;
		double r = this.calcDistance(b);

		double fy   = (force*dy)/r;
		return fy;
	}

	public double calcNetForceExertedByX(Body[] allBodys){ // to calculate net force on body in x-direction.
	
			double fx;
			netForceX = 0.0;
		for(int i =0;i<allBodys.length;i++){
			if(this.equals(allBodys[i])){
	
				continue;
				
			}

			else
			{
				fx =this.calcForceExertedByX(allBodys[i]);
				netForceX = netForceX + fx;
		}
			
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){ // to calculate net force on body in y-direction.
			double fy;
			netForceY = 0.0;
		for(int i =0;i<allBodys.length;i++){
			
			
			if(this.equals(allBodys[i])){
				continue;
				
			}
				
			else
				{fy = this.calcForceExertedByY(allBodys[i]);
				netForceY = netForceY + fy ;}
		}
		return netForceY;
	}
	
	public void update(double dt,double fX,double fY){

		double aX ;
		aX = (fX/this.mass);
		double aY;
		aY = (fY/this.mass);
		xxVel = xxVel + aX*dt;
		yyVel = yyVel + aY*dt;
		xxPos = xxPos + xxVel*dt;
		yyPos = yyPos + yyVel*dt;


	}
	public void draw(){
		imgToDraw = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,this.imgToDraw);
	}

}