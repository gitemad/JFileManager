package View;

import javax.swing.*;
import Controller.*;
import Model.*;

import java.awt.*;

/**
 * @author Emad
 *
 */
public class ToolBarView extends JToolBar {
	
	//model
	private NavigateButtonModel backModel;
	private NavigateButtonModel forwardModel;
	private NavigateButtonModel parentModel;
	private AddressBarModel addressModel;
	private SearchModel searchModel;
	
	//view
	private NavigateButtonView backView;
	private NavigateButtonView forwardView;
	private NavigateButtonView parentView;
	private AddressBarView addressView;
	private SearchView searchView;
	
	//Controller
	private NavigateButtonController backController;
	private NavigateButtonController forwardController;
	private NavigateButtonController parentController;
	
	private JPanel buttonsPanel = new JPanel();
	private BorderLayout layout = new BorderLayout(15, 0);
	private Insets margin = new Insets(0, 5, 0, 15);
	
	/**
	 * Only constructor of class without any parameter requirement
	 */
	public ToolBarView() {
		super();
		
		backModel = new NavigateButtonModel(true);
		forwardModel = new NavigateButtonModel(true);
		parentModel = new NavigateButtonModel(true);
		addressModel = new AddressBarModel("Desktop");
		searchModel = new SearchModel("Search");
		
		backView = new NavigateButtonView(new ImageIcon("img/back.png"));
		forwardView = new NavigateButtonView(new ImageIcon("img/forward.png"));
		parentView = new NavigateButtonView(new ImageIcon("img/parent.png"));
		addressView = new AddressBarView(addressModel);
		searchView = new SearchView(searchModel);
		
		backController = new NavigateButtonController(backModel, backView);
		forwardController = new NavigateButtonController(forwardModel, forwardView);
		parentController = new NavigateButtonController(parentModel, parentView);
		
		
		this.setLayout(layout);		
		this.setMargin(margin);
		buttonsPanel.add(backView);
		buttonsPanel.add(forwardView);
		buttonsPanel.add(Box.createHorizontalStrut(15));
		buttonsPanel.add(parentView);
		
		
		this.add(buttonsPanel, BorderLayout.WEST);
		this.add(addressView, BorderLayout.CENTER);
		this.add(searchView, BorderLayout.EAST);
	}
	
	
}

