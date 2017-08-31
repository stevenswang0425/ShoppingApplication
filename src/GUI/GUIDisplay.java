package GUI;

import java.util.Map;

public class GUIDisplay {

	public GUIDisplay(){
		
	}
	
	//this function is to get the exact location of each product should be present in the GUI
	public int displaytotalpage(Object object, Map<String,Object> objectMap){
		int mapsize = objectMap.size();
		int pagenum = 0;
		int indexloc = 0;
		//if the objects are just enough to display 
		if (mapsize < 6){
			pagenum = 1;
		}
		else{
			if (mapsize % 6 != 0 ){
				pagenum = (mapsize - mapsize % 6) / 6 + 1;
			}
			else {
				pagenum = (mapsize / 6);
			}
		}
		
		for (Map.Entry<String, Object> objectEntry :objectMap.entrySet()){
			if (objectEntry.getValue().equals(object)){
				
			}
		}
		return pagenum;
		
	}
	
	
	
	
}
