package tools;

import java.util.HashMap;

import javax.swing.JComboBox;

public class ViewChiosePamameter {
	public static HashMap<String, Integer> getWeigandComboBox(JComboBox<String> comboBox) {
		/*
		 * WiegandType:26、34 
		 * WiegandIn、WiegandOut：0：不支持、1：正序、2：反序
		 */
		int weigandType = 34;
		int weigand = 0;
		switch (comboBox.getSelectedIndex()) {
		case 0://不支持
			weigand = 0;
			weigandType = 26;
			break;

		case 1://正序维根26
			weigand = 1;
			weigandType = 26;
			break;

		case 2://反序维根26
			weigand = 2;
			weigandType = 26;
			break;

		case 3://正序维根34
			weigand = 1;
			weigandType = 34;
			break;

		case 4://反序维根34
			weigand = 1;
			weigandType = 34;
			break;

		default:
			
			break;
		}
		HashMap<String, Integer> weigandMap = new HashMap<String, Integer>();
		weigandMap.put("weigandType", weigandType);
		weigandMap.put("weigand", weigand);
		return weigandMap;
	}
}
