package com.javaaidev.llmcodeexecutor.mcp;

import com.javaaidev.llmcodeexecutor.executor.core.CodeExecutionConfig;
import com.javaaidev.llmcodeexecutor.executor.core.CodeExecutor;
import com.javaaidev.llmcodeexecutor.executor.core.ExecuteCodeParameters;
import com.javaaidev.llmcodeexecutor.executor.core.ExecuteCodeResult;
import com.javaaidev.llmcodeexecutor.executor.java.JavaCodeExecutor;
import com.javaaidev.llmcodeexecutor.executor.python.PythonCodeExecutor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
public class CodeExecutorTools {

  private final CodeExecutor codeExecutor;

  public CodeExecutorTools() {
    var config = new CodeExecutionConfig(System.getenv("CONTAINER_IMAGE"));
    var language = System.getenv("EXEC_LANG");
    var isJava = "java".equalsIgnoreCase(language);
    this.codeExecutor = isJava ? new JavaCodeExecutor(config) : new PythonCodeExecutor(config);
  }
  
  @McpTool(description = "Execute code", generateOutputSchema = true)
  public ExecuteCodeResult executeCode(ExecuteCodeParameters parameters) {
    return codeExecutor.execute(parameters);
  }
}
