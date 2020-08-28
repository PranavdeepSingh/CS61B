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
}

