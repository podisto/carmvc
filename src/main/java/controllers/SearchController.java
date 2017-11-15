package controllers;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import models.Car;
import services.CarService;
import services.CarServiceImpl;

public class SearchController extends SelectorComposer<Component> {
	
	@Wire
	private Textbox keywordBox;
	
	@Wire("#carListBox")
	private Listbox carListBox;
	
	private CarService carService = new CarServiceImpl();
	
	@Listen("onClick = #searchButton")
	public void search() {
		String keyword = keywordBox.getValue();
		List<Car> results = carService.search(keyword);
		System.out.println(results.size());
		carListBox.setModel(new ListModelList<Car>(results));
	}
}
