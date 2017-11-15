package controllers;

import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;

import models.Car;
import services.CarService;
import services.CarServiceImpl;

public class SearchController extends SelectorComposer<Component> {
	
	@Wire
	private Textbox keywordBox;
	
	@Wire
	private Listbox carListBox;
	
	@Wire
	private Label modelLabel;
	
	@Wire
	private Label makeLabel;
	
	@Wire
	private Label priceLabel;
	
	@Wire
	private Label descriptionLabel;
	
	@Wire
	private Image previewImage;
	
	private CarService carService = new CarServiceImpl();
	
	@Listen("onClick = #searchButton")
	public void search() {
		String keyword = keywordBox.getValue();
		List<Car> results = carService.search(keyword);
		carListBox.setModel(new ListModelList<Car>(results));
	}
	
	@Listen("onSelect = #carListBox")
	public void showDetail() {
		Set<Car> selection = ((Selectable<Car>)carListBox.getModel()).getSelection();
		if (selection != null && !selection.isEmpty()) {
			Car selected = selection.iterator().next();
			previewImage.setSrc(selected.getPreview());
			modelLabel.setValue(selected.getModel());
			makeLabel.setValue(selected.getMake());
			priceLabel.setValue(selected.getPrice().toString());
			descriptionLabel.setValue(selected.getDescription());
		}
	}
}
