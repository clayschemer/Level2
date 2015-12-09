package fractal;
import javax.swing.*;
import java.awt.*;

public class CommandPanel extends JPanel {
	private static final long serialVersionUID = 8797860774678796101L;

	public CommandPanel(FractalView view) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new OrderDownButton(view));
		add(new OrderUpButton(view));
	}
}
