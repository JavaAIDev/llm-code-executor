package com.javaaidev.llmcodeexecutor.executor.core;

public class ExecuteCodeParameters {

  private String code;
  private String codeFileName;
  private OutputFileCollectionConfig outputFileCollectionConfig;

  public ExecuteCodeParameters() {
  }

  public ExecuteCodeParameters(String code) {
    this.code = code;
  }

  public ExecuteCodeParameters(String code, String codeFileName,
      OutputFileCollectionConfig outputFileCollectionConfig) {
    this.code = code;
    this.codeFileName = codeFileName;
    this.outputFileCollectionConfig = outputFileCollectionConfig;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCodeFileName() {
    return codeFileName;
  }

  public void setCodeFileName(String codeFileName) {
    this.codeFileName = codeFileName;
  }

  public OutputFileCollectionConfig getOutputFileCollectionConfig() {
    return outputFileCollectionConfig;
  }

  public void setOutputFileCollectionConfig(
      OutputFileCollectionConfig outputFileCollectionConfig) {
    this.outputFileCollectionConfig = outputFileCollectionConfig;
  }
}
