package ru.geekbrains.client.logic;


import java.util.List;


public interface UIhandler
{

  void addMessage(Message msg);
  void addMessages(List<Message> msgs);

  List<Message> getAllMessages();

  void setUserList(List<String> usernames);

  void showError(Exception e);

}