package fractal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class OrderDownButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 5159918888060394783L;
	private FractalView view;

	public OrderDownButton(FractalView view) {
		super("<");
		this.view = view;
		addActionListener(this);
		this.setToolTipText("Decrease order");
	}

	public void actionPerformed(ActionEvent e) {
		Fractal fractal = view.getFractal();
		if (fractal.getOrder() > 0) {
			
			fractal.setOrder(fractal.getOrder() - 1);
			view.updateDrawing();
		}
	}

}
