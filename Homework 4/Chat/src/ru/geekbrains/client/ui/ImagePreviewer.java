package ru.geekbrains.client.ui;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Objects;

import static java.awt.Image.SCALE_DEFAULT;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.JFileChooser.SELECTED_FILE_CHANGED_PROPERTY;


public class ImagePreviewer
		extends JLabel
{

  private static final Dimension SIZE = new Dimension(256, 256);


  public ImagePreviewer(JFileChooser chooser)
  {
	setPreferredSize(SIZE);
	setBorder(createEtchedBorder());

	chooser.addPropertyChangeListener(e -> update(e));
  }


  private void update(PropertyChangeEvent event)
  {
	String prop = event.getPropertyName();

	if (Objects.equals(prop, SELECTED_FILE_CHANGED_PROPERTY))
	{
	  File f = (File) event.getNewValue();
	  loadlmage(f);
	}
  }


  private void loadlmage(File f)
  {
	ImageIcon icon = new ImageIcon(f.getPath());

	int width = getWidth();

	if (icon.getIconWidth() > width)
	{
	  Image img = icon.getImage();
	  img = img.getScaledInstance(width, -1, SCALE_DEFAULT);
	  icon = new ImageIcon(img);
	}

	setIcon(icon);
	repaint();
  }

}