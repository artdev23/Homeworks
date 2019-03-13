package ru.geekbrains.client.ui.settings;


import ru.geekbrains.client.logic.NetConnection;
import ru.geekbrains.client.ui.FileNameExtensionFilter;
import ru.geekbrains.client.ui.ImagePreviewer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.BorderLayout.*;
import static java.awt.GridBagConstraints.*;
import static javax.imageio.ImageIO.read;


public class AccountTab
		extends JPanel
{

  private final NetConnection conn;
  private JPanel panel;
  private BufferedImage img;
  private JLabel lblImg;
  private JButton btnLoadImg;
  private JFileChooser chooser;


  public AccountTab(NetConnection connection)
  {
	conn = connection;

	configFileDialog();
	addElems();
	addEventHandlers();
  }


  private void addElems()
  {
	panel = new JPanel(new GridBagLayout());

	GridBagConstraints cs = new GridBagConstraints();

	cs.fill = HORIZONTAL;

	lblImg = new JLabel();
	cs.gridx = 0;
	cs.gridy = 0;
	cs.gridwidth = 1;
	panel.add(lblImg, cs);

	btnLoadImg = new JButton("Choose photo");
	cs.gridx = 1;
	cs.gridy = 0;
	cs.gridwidth = 1;
	panel.add(btnLoadImg, cs);

	add(panel, BorderLayout.PAGE_START);
  }


  private void configFileDialog()
  {
	chooser = new JFileChooser();

	chooser.setAcceptAllFileFilterUsed(false);

	FileFilter filter = new FileNameExtensionFilter("gif", "jpg", "jpeg", "png");
	chooser.setFileFilter(filter);

	ImagePreviewer previewer = new ImagePreviewer(chooser);
	chooser.setAccessory(previewer);
  }


  private void addEventHandlers()
  {
	btnLoadImg.addActionListener(e -> loadImage());
  }


  private void loadImage()
  {
	try
	{
	  int result = chooser.showDialog(btnLoadImg, "Select");

	  if (result != JFileChooser.APPROVE_OPTION)
		return;

	  String path = chooser.getSelectedFile().getPath();
	  File file = new File(path);
	  img = read(file);

	  ImageIcon icon = new ImageIcon(img);
	  lblImg.setIcon(icon);
//	  pack();
	}
	catch (IOException e)
	{
	  e.printStackTrace();
	}
  }


}