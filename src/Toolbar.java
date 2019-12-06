import javax.swing.*;

import Controller.NavigateButtonController;
import Model.NavigateButtonModel;
import View.NavigateButtonView;

import java.awt.*;

/**
 * @author Emad
 *
 */
public class Toolbar extends JToolBar {
	
	//view
	private NavigateButtonView backView;
	private NavigateButtonView forwardView;
	private NavigateButtonView parentView;
	
	//model
	private NavigateButtonModel backModel;
	private NavigateButtonModel forwardModel;
	private NavigateButtonModel parentModel;
	
	//Controller
	private NavigateButtonController backController;
	private NavigateButtonController forwardController;
	private NavigateButtonController parentController;
	
	private JPanel buttonsPanel = new JPanel();
	private JTextField address = new AddressBar();
	private JTextField search = new Search();
	private BorderLayout layout = new BorderLayout(15, 0);
	private Insets margin = new Insets(0, 5, 0, 15);
	
	public Toolbar() {
		super();
		
		backView = new NavigateButtonView(new ImageIcon("img/back.png"));
		forwardView = new NavigateButtonView(new ImageIcon("img/forward.png"));
		parentView = new NavigateButtonView(new ImageIcon("img/parent.png"));
		
		backModel = new NavigateButtonModel(true);
		forwardModel = new NavigateButtonModel(true);
		parentModel = new NavigateButtonModel(true);
		
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
		this.add(address, BorderLayout.CENTER);
		this.add(search, BorderLayout.EAST);
	}
	
	
}
