package com.greybirdsoft.genki.commands;

import com.greybirdsoft.genki.GenkiExecution;

public class Copy extends BaseCommand {
  final String from;
  String to;

  public Copy(CommandBuilder commandBuilder, String from) {
    super(commandBuilder);
    this.from = from;
  }

  public GenkiExecution to(String to) {
    if (to == null || to.isEmpty()) {
      throw new IllegalArgumentException("'to' command need a 'from' source");
    }

    this.to = to;
    return commandBuilder.command(buildCommand());
  }

  @Override
  protected String buildCommand() {
    String command = "cp " + from + " " + to;
    return command;
  }
}
