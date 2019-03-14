package ru.geekbrains.client.ui.settings;


import ru.geekbrains.client.logic.NetConnection;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;
import static javax.swing.JTabbedPane.LEFT;


public class SettingsWindow
		extends JDialog
{

  private final NetConnection conn;
  private JTabbedPane pane;
  private JButton btnOK;
  private JButton btnCancel;
  private JPanel pnlButtons;

  private static final String TITLE = "Settings";


  public SettingsWindow(JFrame parent, NetConnection connection)
  {
	super(parent, TITLE, true);

	conn = connection;

	setMinimumSize(parent.getSize());

	addElems(connection);

	btnOK.addActionListener(e -> applySettings());
	btnCancel.addActionListener(e -> dispose());

	pack();
	setResizable(false);
	setLocationRelativeTo(parent);
	setVisible(false);
  }


  private void addElems(NetConnection connection)
  {
	pane = new JTabbedPane(LEFT);
	pane.addTab("General", new GeneralTab());
	pane.addTab("Account", new AccountTab(connection));

	btnOK = new JButton("OK");
	btnCancel = new JButton("Cancel");

	pnlButtons = new JPanel();
	pnlButtons.add(btnOK);
	pnlButtons.add(btnCancel);
	add(pnlButtons);

	Container container = getContentPane();
	container.add(pane, CENTER);
	container.add(pnlButtons, PAGE_END);
  }


  private void applySettings()
  {
	;
  }

}