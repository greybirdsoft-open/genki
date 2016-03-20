package com.greybirdsoft.genki.commands;

import com.greybirdsoft.genki.GenkiExecution;
import com.greybirdsoft.genki.OnFinishTaskListener;

public class CommandBuilder {
  final private OnFinishTaskListener onFinishTaskListener;

  public CommandBuilder(final OnFinishTaskListener onFinishTaskListener) {
    this.onFinishTaskListener = onFinishTaskListener;
  }

  public GenkiExecution command(String command) {
    return new GenkiExecution(onFinishTaskListener, command);
  }

  public GenkiExecution commands(String... command) {
    return new GenkiExecution(onFinishTaskListener, command);
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
