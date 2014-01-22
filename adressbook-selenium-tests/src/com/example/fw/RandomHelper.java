package com.example.fw;

import java.util.Random;
import com.example.utils.SortedListOf;

public class RandomHelper extends HelperBase {

	public RandomHelper(ApplicationManager manager) {
		super(manager);
	}

	
	public SortedListOf<Integer> randomIndexesList() {
		Random rnd = new Random();
		int maxCount = manager.getGroupHelper().getGroups().size();
		SortedListOf<Integer> indexesList = new SortedListOf<Integer>();
	    if (maxCount == 0) 
	    	throw new Error("Нет групп для удаления"); 
		int countForDelete = rnd.nextInt(maxCount); 
		if (countForDelete == 0)
	    	countForDelete = 1;
		for (int i = 0; i < countForDelete; i++) {
			int index = rnd.nextInt(maxCount);
			indexesList.add(index);
		}
		//удаляем повторы
		for (int i = 0; i < indexesList.size() - 1; i++) {
			for (int j = i + 1; j < indexesList.size();) {
				if (indexesList.get(i) == indexesList.get(j)) 
					indexesList.remove(i);
				else j++;
			}
		}
		return indexesList;
	}

}
