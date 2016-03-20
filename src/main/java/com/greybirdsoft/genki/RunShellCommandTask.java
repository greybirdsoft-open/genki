package com.greybirdsoft.genki;

import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeoutException;
import timber.log.Timber;

//TODO remove SafeAsynctask for other PR
public class RunShellCommandTask extends SafeAsyncTask<Integer> {
  public static final int SHELL_COMMAND_OK = 0;
  public static final int SHELL_COMMAND_COMPLETED = 100;
  public static final int SHELL_COMMAND_ERROR = -1;
  public static final int SHELL_COMMAND_ERROR_INTERRUPTED = -2;
  public static final int SHELL_COMMAND_ERROR_IO = -3;
  public static final int SHELL_COMMAND_ERROR_TIMEOUT = -4;
  public static final int SHELL_COMMAND_ERROR_ROOT_DENIED = -5;
  public static final int NO_NEW_NUMBER_CONFIGURED = -6;

  public RunShellCommandTask(final OnFinishTaskListener onCommandListener, String... commands) {
    for (String command : commands) {
      if (command == null) {
        command = "";
      }

      Timber.d("RunShellCommandTask execute: %s", command);

      Command commandCapture = new Command(0, command) {
        @Override
        public void commandCompleted(int id, int exitCode) {
          super.commandCompleted(id, exitCode);
          onCommandListener.onFinishTask(SHELL_COMMAND_COMPLETED);
        }
      };

      try {
        RootTools.runShellCommand(RootTools.getShell(true), commandCapture);
        onCommandListener.onFinishTask(SHELL_COMMAND_OK);
        Timber.i("RunShellCommandTask OK ");
      } catch (InterruptedIOException e) {
        Timber.e(e, "RunShellCommandTask ERROR ");
        onCommandListener.onFinishTask(SHELL_COMMAND_ERROR_INTERRUPTED);
      } catch (IOException e) {
        Timber.e(e, "RunShellCommandTask ERROR ");
        onCommandListener.onFinishTask(SHELL_COMMAND_ERROR_IO);
      } catch (TimeoutException e) {
        Timber.e(e, "RunShellCommandTask ERROR ");
        onCommandListener.onFinishTask(SHELL_COMMAND_ERROR_TIMEOUT);
      } catch (RootDeniedException e) {
        Timber.e(e, "RunShellCommandTask ERROR ");
        onCommandListener.onFinishTask(SHELL_COMMAND_ERROR_ROOT_DENIED);
      }
    }
  }

  @Override
  public Integer call() throws Exception {
    return SHELL_COMMAND_OK;
  }
}