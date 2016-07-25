package com.greybirdsoft.genki;

import com.greybirdsoft.genki.commands.CommandBuilder;
import com.stericson.RootTools.RootTools;

public class Genki {

  private static GenkiInstance instance;

  public Genki(Builder builder) {
    RootTools.debugMode = builder.isDebug;
  }

  public static Builder initialize() {
    if (instance == null) {
      instance = new GenkiInstance();
    }

    return new Builder();
  }

  public static void checkRoot(RootListener rootListener) {
    instance.checkRoot(rootListener);
  }

  public static CommandBuilder execute(final CommandExecutionListener commandExecutionListener) {
    return instance.execute(commandExecutionListener);
  }

  public static class Builder {
    boolean isDebug = false;

    public Builder setDebug(boolean isDebug) {
      this.isDebug = isDebug;

      return this;
    }

    public Genki build() {
      return new Genki(this);
    }
  }
}