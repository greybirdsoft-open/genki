package com.greybirdsoft.genki;

import com.greybirdsoft.genki.commands.CommandBuilder;

public class GenkiExecution {
  private final CommandExecutionListener commandExecutionListener;

  public GenkiExecution(CommandExecutionListener commandExecutionListener, String command) {
    this.commandExecutionListener = commandExecutionListener;
    new RunShellCommandTask(commandExecutionListener, command);
  }

  public GenkiExecution(CommandExecutionListener commandExecutionListener, String... command) {
    this.commandExecutionListener = commandExecutionListener;
    new RunShellCommandTask(commandExecutionListener, command);
  }

  public CommandBuilder and() {
    return new CommandBuilder(commandExecutionListener);
  }
}