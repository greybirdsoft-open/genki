package com.greybirdsoft.genki;

import com.greybirdsoft.genki.commands.CommandBuilder;

public class GenkiExecution {
  private final OnFinishTaskListener onFinishTaskListener;

  public GenkiExecution(OnFinishTaskListener onFinishTaskListener, String command) {
    this.onFinishTaskListener = onFinishTaskListener;
    new RunShellCommandTask(onFinishTaskListener, command);
  }

  public GenkiExecution(OnFinishTaskListener onFinishTaskListener, String... command) {
    this.onFinishTaskListener = onFinishTaskListener;
    new RunShellCommandTask(onFinishTaskListener, command);
  }

  public CommandBuilder and() {
    return new CommandBuilder(onFinishTaskListener);
  }
}
