package com.greybirdsoft.genki.commands;

public abstract class BaseCommand {

  final CommandBuilder commandBuilder;

  protected BaseCommand(CommandBuilder commandBuilder) {
    this.commandBuilder = commandBuilder;
  }

  protected abstract String buildCommand();
}
