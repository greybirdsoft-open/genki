package com.greybirdsoft.genki.commands;

import com.greybirdsoft.genki.GenkiExecution;
import com.greybirdsoft.genki.CommandExecutionListener;

public class CommandBuilder {
  final private CommandExecutionListener commandExecutionListener;

  public CommandBuilder(final CommandExecutionListener commandExecutionListener) {
    this.commandExecutionListener = commandExecutionListener;
  }

  public GenkiExecution command(String command) {
    return new GenkiExecution(commandExecutionListener, command);
  }

  public GenkiExecution commands(String... command) {
    return new GenkiExecution(commandExecutionListener, command);
  }

  public Copy copy(String from) {
    return new Copy(this, from);
  }

  public Remove remove() {
    return new Remove(this);
  }

  public Compress compress(String filename) {
    return new Compress(this, filename);
  }

  public Uncompress uncompress() {
    return new Uncompress(this);
  }

  public GenkiExecution kill(String appProcessName){
    return command("killall " + appProcessName);
  }

  public GenkiExecution fullPermissions(String path){
    return command("chmod 777 " + path);
  }

}