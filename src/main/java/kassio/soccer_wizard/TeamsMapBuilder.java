package kassio.soccer_wizard;

import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class TeamsMapBuilder {

	private HashMap<String, Sheet> teamsMap;

	public TeamsMapBuilder(Workbook workbook){
		teamsMap = new HashMap<String, Sheet>();
//		teamsMap.put("time1", workbook.getSheet("AmericaMG"));
//		teamsMap.put("time2", workbook.getSheet("AtleticoGO"));
//		teamsMap.put("time3", workbook.getSheet("AtleticoMG"));
//		teamsMap.put("time4", workbook.getSheet("AtleticoPR"));
//		teamsMap.put("time5", workbook.getSheet("Avai"));
//		teamsMap.put("time6", workbook.getSheet("Bahia"));
//		teamsMap.put("time7", workbook.getSheet("Botafogo"));
//		teamsMap.put("time8", workbook.getSheet("Ceara"));
//		teamsMap.put("time9", workbook.getSheet("Corinthians"));
//		teamsMap.put("time10", workbook.getSheet("Coritiba"));
//		teamsMap.put("time11", workbook.getSheet("Cruzeiro"));
//		teamsMap.put("time12", workbook.getSheet("Figueirense"));
//		teamsMap.put("time13", workbook.getSheet("Flamengo"));
//		teamsMap.put("time14", workbook.getSheet("Fluminense"));
//		teamsMap.put("time15", workbook.getSheet("Gremio"));
//		teamsMap.put("time16", workbook.getSheet("Internacional"));
//		teamsMap.put("time17", workbook.getSheet("Palmeiras"));
//		teamsMap.put("time18", workbook.getSheet("Santos"));
//		teamsMap.put("time19", workbook.getSheet("SaoPaulo"));
//		teamsMap.put("time20", workbook.getSheet("Vasco"));
		
		teamsMap.put("time1", workbook.getSheet("AC Milan"));
		teamsMap.put("time2", workbook.getSheet("Atalanta"));
		teamsMap.put("time3", workbook.getSheet("Bologna"));
		teamsMap.put("time4", workbook.getSheet("Brescia"));
		teamsMap.put("time5", workbook.getSheet("Chievo"));
		teamsMap.put("time6", workbook.getSheet("Fiorentina"));
		teamsMap.put("time7", workbook.getSheet("Inter"));
		teamsMap.put("time8", workbook.getSheet("Juventus"));
		teamsMap.put("time9", workbook.getSheet("Lazio"));
		teamsMap.put("time10", workbook.getSheet("Lecce"));
		teamsMap.put("time11", workbook.getSheet("Parma"));
		teamsMap.put("time12", workbook.getSheet("Perugia"));
		teamsMap.put("time13", workbook.getSheet("Piacenza"));
		teamsMap.put("time14", workbook.getSheet("Roma"));
		teamsMap.put("time15", workbook.getSheet("Torino"));
		teamsMap.put("time16", workbook.getSheet("Udinese"));
		teamsMap.put("time17", workbook.getSheet("Venezia"));
		teamsMap.put("time18", workbook.getSheet("Verona"));
	}

	public HashMap<String, Sheet> getTeamsMap() {
		return teamsMap;
	}

}