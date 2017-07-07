package kassio;

import kassio.panel.SoccerWizardGUI;

import java.util.logging.Level;

import static kassio.GlobalData.*;

public class SoccerWizard {

	public static void main(String[] args) {

		logger.log(Level.INFO, "Let's get the party started!\n");
 
		new SoccerWizardGUI().setVisible(true);
	}
}