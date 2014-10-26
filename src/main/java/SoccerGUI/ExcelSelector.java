package SoccerGUI;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import kassio.soccer_wizard.TeamsMapBuilder;

public class ExcelSelector {

	public TeamsMapBuilder escolhe() {
		TeamsMapBuilder championship = null;

		try {
			JFileChooser chooser = new JFileChooser(
					"C:\\Users\\Kássio\\workspace\\soccer_wizard");
			chooser.setFileFilter(new FileNameExtensionFilter(".xls", "xls"));

			int retorno = chooser.showOpenDialog(null);

			if (retorno == JFileChooser.APPROVE_OPTION) {
				championship = new TeamsMapBuilder(Workbook.getWorkbook(chooser
						.getSelectedFile()));
				// Workbook workbook =
				// Workbook.getWorkbook(chooser.getSelectedFile());
				// FileReader reader = new
				// FileReader(chooser.getSelectedFile());

				String mensagem = "Campeonato carregado!";
				JOptionPane.showMessageDialog(null, mensagem);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return championship;
	}

}
