package com.greybirdsoft.genki.commands;

import com.greybirdsoft.genki.GenkiExecution;

public class Remove extends BaseCommand {
  String file;
  String folder;

  protected Remove(CommandBuilder commandBuilder) {
    super(commandBuilder);
  }

  @Override
  protected String buildCommand() {
    if (file.isEmpty() && folder.isEmpty()) {
      throw new IllegalArgumentException("You must define or File or Folder");
    }

    String command = null;

    if (!file.isEmpty()) {
      command = "rm " + file;
    }

    if (!folder.isEmpty()) {
      command = "rm -r " + folder;
    }

    return command;
  }


  public GenkiExecution folder(String folderPath){
    if (folderPath == null || folderPath.isEmpty()) {
      throw new IllegalArgumentException("You must define a folder path");
    }

    this.folder = folderPath;
    return commandBuilder.command(buildCommand());
  }

  public GenkiExecution file(String file){
    if (file == null || file.isEmpty()) {
      throw new IllegalArgumentException("You must define a file path");
    }

    this.file = file;
    return commandBuilder.command(buildCommand());
  }
}
