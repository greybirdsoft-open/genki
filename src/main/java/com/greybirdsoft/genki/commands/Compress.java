package com.greybirdsoft.genki.commands;

import com.greybirdsoft.genki.GenkiExecution;

public class Compress extends BaseCommand {
  private String compressedFilename;
  private String folderToCompress;


  protected Compress(CommandBuilder commandBuilder, String compressedFilename) {
    super(commandBuilder);
    this.compressedFilename = compressedFilename;
  }

  @Override
  protected String buildCommand() {
    return "tar -cf " + compressedFilename + " " + folderToCompress;
  }

  public GenkiExecution folder(String folderToCompress) {
    this.folderToCompress = folderToCompress;
    return commandBuilder.command(buildCommand());
  }
}
