package fractal;

import mountain.Mountain;
import koch.Koch;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Koch(300);
		fractals[1] = new Mountain(90.0);
	    new FractalView(fractals, "Fraktaler");
	}

}
