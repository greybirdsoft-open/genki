package com.greybirdsoft.genki;

import android.content.Context;
import com.greybirdsoft.genki.commands.CommandBuilder;
import com.stericson.RootTools.RootTools;

public class Genki {

  private static GenkiInstance instance;

  public Genki(Builder builder) {
    RootTools.debugMode = builder.isDebug;
  }

  public static Builder initialize(Context context) {
    if (instance == null) {
      instance = new GenkiInstance(context);
    }

    return new Builder();
  }

  public static void checkRoot(RootListener rootListener) {
    instance.checkRoot(rootListener);
  }

  public static CommandBuilder execute(final OnFinishTaskListener onFinishTaskListener) {
    return instance.execute(onFinishTaskListener);
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
