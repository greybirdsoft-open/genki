package com.greybirdsoft.genki.commands;

import com.greybirdsoft.genki.GenkiExecution;

public class Uncompress extends BaseCommand {
  String filePath;

  public Uncompress(CommandBuilder commandBuilder) {
    super(commandBuilder);
  }

  public GenkiExecution file(String filePath) {
    if (filePath == null || filePath.isEmpty()) {
      throw new IllegalArgumentException("'filePath' command mandatory");
    }

    this.filePath = filePath;
    return commandBuilder.command(buildCommand());
  }

  @Override
  protected String buildCommand() {
    String command = "tar -xf " + filePath + " -C /";
    return command;
  }
}
