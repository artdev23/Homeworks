package ru.geekbrains.client.ui;


import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;


public class FileNameExtensionFilter
		extends FileFilter
{

  private final Set<String> extensions;


  public FileNameExtensionFilter(String... ext)
  {
	extensions = new HashSet<>(asList(ext));
  }


  @Override
  public boolean accept(File f)
  {
	String[] parts = f.getName().split("\\.");

	if (parts.length == 0)
	  return false;

	String ext = parts[parts.length - 1];

	return extensions.contains(ext);
  }


  @Override
  public String getDescription()
  {
	return "Image files";
  }

}