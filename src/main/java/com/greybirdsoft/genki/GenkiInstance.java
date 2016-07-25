package com.greybirdsoft.genki;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.greybirdsoft.genki.commands.CommandBuilder;
import com.stericson.RootTools.RootTools;

public final class GenkiInstance {

  private String previousPackageName;
  private PackageManager packageManager;
  private RootListener rootListener;

  public void checkRoot(RootListener rootListener) {
    this.rootListener = rootListener;
    checkRootFromRootTools();
  }

  public CommandBuilder execute(CommandExecutionListener commandExecutionListener) {
    return new CommandBuilder(commandExecutionListener);
  }


  //  public void restorePreviousDataApp(BackupManager backupManager, final Context context) {
  //    backupManager.movePreviousBackupsToCurrentApp(previousPackageName,
  //        result -> {
  //          switch (result) {
  //            case RunShellCommandTask.SHELL_COMMAND_COMPLETED:
  //              uninstallApp(context);
  //              break;
  //          }
  //        });
  //  }

  private void checkRootFromRootTools() {
    RootStatus rootStatus;
    if (RootTools.isRootAvailable()) {
      try {
        if (RootTools.isAccessGiven()) {
          rootStatus = RootStatus.ROOT_GRANT;
        } else {
          rootStatus = RootStatus.ROOT_DENIED;
        }
      } catch (Exception e) {
        rootStatus = RootStatus.ROOT_DENIED;
      }
    } else {
      rootStatus = RootStatus.ROOT_DENIED;
    }

    if (rootStatus == RootStatus.ROOT_GRANT) {
      rootListener.onRootAndBusyBoxOk();
    } else {
      rootListener.onRootDenied();
    }
  }

  private boolean checkBusyBox() {
    boolean installed = false;

    try {
      if (!TextUtils.isEmpty(RootTools.getBusyBoxVersion()) || RootTools.getBusyBoxApplets()
          .contains("killall")) {
        installed = true;
      }
    } catch (Exception e) {
      Log.e(getClass().getName(), e.getLocalizedMessage() + ": GetBusyBoxApplets ERROR!");
    }

    return installed;
  }
}