package fractal;
import javax.swing.*;
import java.awt.event.*;

public class OrderUpButton extends JButton implements ActionListener {
	private static final long serialVersionUID = -3468550196883447682L;
	private FractalView view;

	public OrderUpButton(FractalView view) {
		super(">");
		this.view = view;
		addActionListener(this);
		this.setToolTipText("Increase order");
	}

	public void actionPerformed(ActionEvent e) {
		Fractal fractal = view.getFractal();
		fractal.setOrder(fractal.getOrder() + 1);
		view.updateDrawing();
	}

}
