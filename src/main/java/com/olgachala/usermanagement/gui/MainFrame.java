package com.olgachala.usermanagement.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.olgachala.usermanagement.User;
import com.olgachala.usermanagement.db.DaoFactory;
import com.olgachala.usermanagement.db.userDAO;
import com.olgachala.usermanagement.util.Messages;


public class MainFrame extends JFrame {
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private AddPanel addPanel;
    private userDAO dao;
    private EditPanel editPanel;
	private DetailsPanel detailsPanel;

	public MainFrame() {
	super();
	dao = DaoFactory.getInstance().getUserDao();
	initialize();
	}
	
	public userDAO getDao() {
		return dao;
	}
	
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}
	
	private JPanel getContentPanel() {
		if (contentPanel==null) {
			contentPanel= new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(),BorderLayout.CENTER);
		}
		
		return contentPanel;
	}

	private JPanel getBrowsePanel() {
		if (browsePanel==null) {
			browsePanel= new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}

	public static void main(String[] args) {
		MainFrame frame= new MainFrame();
		frame.setVisible(true);
	}

	public void showAddPanel() {
		showPanel(getAddPanel());
	}

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
	}

	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
		
	}
	
	private AddPanel getAddPanel() {
		if(addPanel==null){
			addPanel=new AddPanel(this);
		}
		return addPanel;
	}

	

	public void showEditPanel(User user) {
        getEditPanel().setUser(user);
        showPanel(getEditPanel());
        
    }


    private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }
    
    
    	
	private DetailsPanel getDetailsPanel() {
		if (detailsPanel == null) {
			detailsPanel = new DetailsPanel(this);
		}
		return detailsPanel;
	}
	
	public void showDetailsPanel(User user) {
		getDetailsPanel().setUser(user);
		showPanel(getDetailsPanel());

	}
}
