package mountain;

import java.util.LinkedList;
import java.util.List;

import fractal.*;

public class Mountain extends Fractal {
	private Point a, b, c;
	private double dev, orderedDev = 0;
	private List<Side> list;

	public Mountain(double dev) {
		super();
		a = new Point(100.0, 430.0);
		b = new Point(270.0, 150.0);
		c = new Point(500.0, 480.0);
		this.dev = dev;
		this.list = new LinkedList<Side>();
	}

	public String getTitle() {
		return "Bergsmassiv";
	}

	public void draw(TurtleGraphics turt) {
		orderedDev = dev;
		int count = order;
		while (count > 0) {
			orderedDev = orderedDev / 2;
			count--;
		}
		fractalLine(turt, order, a, b, c);
	}

	private void fractalLine(TurtleGraphics turt, int order, Point newA,
			Point newB, Point newC) {
		boolean existAB = false, existBC = false, existCA = false;
		if (order == 0) {
			turt.moveTo(newA.getX(), newA.getY());
			turt.penDown();
			turt.forwardTo(newB.getX(), newB.getY());
			turt.forwardTo(newC.getX(), newC.getY());
			turt.forwardTo(newA.getX(), newA.getY());
		} else {
			Point midAB = null, midBC = null, midCA = null;
			if (list.size() > 0) {
				for (Side s : list) {
					if (newA.equals(s.getP1()) && newB.equals(s.getP2())) {
						midAB = s.getM();
						list.remove(s);
						existAB = true;
						break;
					}
				}
				for (Side s : list) {
					if (newB.equals(s.getP1()) && newC.equals(s.getP2())) {
						midBC = s.getM();
						list.remove(s);
						existBC = true;
						break;
					}
				}
				for (Side s : list) {
					if (newC.equals(s.getP1()) && newA.equals(s.getP2())) {
						midCA = s.getM();
						list.remove(s);
						existCA = true;
						break;
					}
				}
			}
			if (!existAB) {
				midAB = new Point(calcMidX(newA.getX(), newB.getX()), calcMidY(newB.getY(), newA.getY()));
				list.add(new Side(newA, newB, midAB));
			}
			if (!existBC) {
				midBC = new Point(calcMidX(newB.getX(), newC.getX()), calcMidY(newB.getY(), newC.getY()));
				list.add(new Side(newB, newC, midBC));
			}
			if (!existCA) {
				midCA = new Point(calcMidX(newA.getX(), newC.getX()), calcMidY(newA.getY(), newC.getY()));
				list.add(new Side(newC, newA, midCA));
			}
			fractalLine(turt, order - 1, newA, midAB, midCA);
			fractalLine(turt, order - 1, midAB, newB, midBC);
			fractalLine(turt, order - 1, midCA, midBC, newC);
			fractalLine(turt, order - 1, midAB, midCA, midBC);
		}
	}
	
	private double calcMidX(double paramOne, double paramTwo) {
		return paramOne + (paramTwo - paramOne)/ 2;
	}
	
	private double calcMidY(double paramOne, double paramTwo) {
		return paramOne + (paramTwo - paramOne) / 2
		+ RandomUtilities.randFunc(orderedDev);
	}
}
