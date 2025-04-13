package com.javaaidev.llmcodeexecutor.executor.java;

import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeConfiguration;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeReturnType;
import java.lang.reflect.Type;

public class ExecuteCode extends AbstractExecuteCode {

  public ExecuteCode(final ExecuteCodeConfiguration config) {
    super(config);
  }

  @Override
  public Type getRequestType() {
    return ExecuteCodeParameters.class;
  }

  @Override
  public ExecuteCodeReturnType call(final ExecuteCodeParameters parameters) {
    return new JavaCodeExecutor(config).execute(parameters);
  }
}
