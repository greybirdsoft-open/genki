package com.greybirdsoft.genki;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.greybirdsoft.genki.commands.CommandBuilder;
import com.stericson.RootTools.RootTools;

public final class GenkiInstance {

  private final Context context;
  //  private Preferences preferences;
//  private Instrumentation instrumentation;
  private String previousPackageName;
  private PackageManager packageManager;
  private RootListener rootListener;

  GenkiInstance(Context context) {
    this.context = context;
  }


//  public void onActivityCreate(Activity activity) {
////    preferences = Preferences.getInstance(activity.getApplicationContext());
////    previousPackageName = activity.getString(R.string.previous_package);
//    packageManager = activity.getPackageManager();
////    askForRootAccess();
//  }


  public void checkRoot(RootListener rootListener) {
    this.rootListener = rootListener;
    checkRootFromRootTools();
  }

  public CommandBuilder execute(OnFinishTaskListener onFinishTaskListener) {
    return new CommandBuilder(onFinishTaskListener);
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

//    preferences.setRootAccess(rootStatus);

    if (rootStatus == RootStatus.ROOT_GRANT) {
      rootListener.onRootAndBusyBoxOk();
    } else {
      rootListener.onRootDenied();
    }
  }

//  private void askForRootAccess() {
//    switch (preferences.getRootAccess()) {
//      case ROOT_DENIED:
//        instrumentation.sendEvent(R.string.category_app, R.string.action_root_access, "false");
//        Log.e(getClass().getName(), "No ROOT granted:(");
//
//        rootListener.OnRootDenied();
//        break;
//
//      case ROOT_GRANT:
//        instrumentation.sendEvent(R.string.category_app, R.string.action_root_access, "true");
//        Log.i(getClass().getName(), "ROOT Access ;)");
//
//        if (checkBusyBox()) {
//          if (Utils.isAppInstalled(previousPackageName, packageManager)) {
//            rootListener.OnPreviousAppInstalled();
//          } else {
//            rootListener.OnRootAndBusyBoxOk();
//          }
//        } else {
//          rootListener.OnBusyboxError();
//        }
//        break;
//
//      case ROOT_NOT_ANSWERED:
//        Log.e(getClass().getName(), "ROOT_NOT_ANSWERED :O");
//        RootStatus rootStatus;
//        if (RootTools.isRootAvailable()) {
//          try {
//            if (RootTools.isAccessGiven()) {
//              rootStatus = RootStatus.ROOT_GRANT;
//            } else {
//              rootStatus = RootStatus.ROOT_DENIED;
//            }
//          } catch (Exception e) {
//            rootStatus = RootStatus.ROOT_DENIED;
//          }
//        } else {
//          rootStatus = RootStatus.ROOT_DENIED;
//        }
//
//        preferences.setRootAccess(rootStatus);
//
//        if (rootStatus == RootStatus.ROOT_GRANT) {
//          rootListener.OnRootAndBusyBoxOk();
//        } else {
//          rootListener.OnRootDenied();
//        }
//        break;
//
//      default:
//        rootListener.OnRootDenied();
//        break;
//    }
//  }

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


//  public interface RootListener {
//
//
//    void OnPreviousAppInstalled();
//  }
}
