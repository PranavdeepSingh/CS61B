public class NBody{

public static double readRadius(String fileName){ //reads and returns radius of universe from file
In in = new In(fileName);
int nPlanets = in.readInt();
double rad = in.readDouble();

return rad;
}


public static Body[] readBodies(String fileName){ // returns an array of planets.
In in = new In(fileName);

int nPlanets = in.readInt();
double rad = in.readDouble();
Body[] arr = new Body[nPlanets];
int i =0;
while(!in.isEmpty() && i<arr.length ){
	
	double xP  = in.readDouble();
	double yP  = in.readDouble();
	double xV  = in.readDouble();
	double yV  = in.readDouble();
	double m   = in.readDouble();
	String img = in.readString();

	
	arr[i] =  new Body(xP,yP,xV,yV,m,img);
	i++;

}
return  arr;

}


public static void main(String args[]){
	double T  = Double.parseDouble(args[0]);
	double dt = Double.parseDouble(args[1]);
	String filename = args[2];
	In in = new In(filename);
	int nPlanets = in.readInt();
	double r = readRadius(filename);
	Body[] p = readBodies(filename);
	
	
	
	StdDraw.setScale(-r,r);//set the scale to radius of universe
	StdDraw.clear();
	
	StdDraw.enableDoubleBuffering();//draws on offscreen canvas
	for(double i = 0.0;i<=T;){
		Double[] xForces = new Double[nPlanets];
		Double[] yForces = new Double[nPlanets];
		for(int j = 0;j<p.length;j++){
			xForces[j] = p[j].calcNetForceExertedByX(p);			//animation
			yForces[j] = p[j].calcNetForceExertedByY(p);
		}	
		for(int j = 0;j<p.length;j++){
			p[j].update(i,xForces[j],yForces[j]);
		}
		

	StdDraw.picture(0,0,"images/starfield.jpg");
	for(int k =0;k<p.length;k++){
		
		
		p[k].draw();
	}
	StdDraw.show();
	StdDraw.pause(10);
	i= i+dt;

	}

	StdOut.printf("%d\n", p.length);
	StdOut.printf("%.2e\n", r);
	for (int i = 0; i < p.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  p[i].xxPos, p[i].yyPos, p[i].xxVel,
                  p[i].yyVel, p[i].mass, p[i].imgFileName);   
}




}



}

