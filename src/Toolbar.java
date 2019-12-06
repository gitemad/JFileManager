import javax.swing.*;

import Controller.*;
import Model.*;
import View.*;

import java.awt.*;

/**
 * @author Emad
 *
 */
public class Toolbar extends JToolBar {
	
	//model
	private NavigateButtonModel backModel;
	private NavigateButtonModel forwardModel;
	private NavigateButtonModel parentModel;
	private AddressBarModel addressModel;
	
	//view
	private NavigateButtonView backView;
	private NavigateButtonView forwardView;
	private NavigateButtonView parentView;
	private AddressBarView addressView;
	
	//Controller
	private NavigateButtonController backController;
	private NavigateButtonController forwardController;
	private NavigateButtonController parentController;
	
	private JPanel buttonsPanel = new JPanel();
	private JTextField search = new Search();
	private BorderLayout layout = new BorderLayout(15, 0);
	private Insets margin = new Insets(0, 5, 0, 15);
	
	public Toolbar() {
		super();
		
		backModel = new NavigateButtonModel(true);
		forwardModel = new NavigateButtonModel(true);
		parentModel = new NavigateButtonModel(true);
		addressModel = new AddressBarModel("Desktop");
		
		backView = new NavigateButtonView(new ImageIcon("img/back.png"));
		forwardView = new NavigateButtonView(new ImageIcon("img/forward.png"));
		parentView = new NavigateButtonView(new ImageIcon("img/parent.png"));
		addressView = new AddressBarView(addressModel);
		
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
		this.add(search, BorderLayout.EAST);
	}
	
	
}
