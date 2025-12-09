package com.javaaidev.llmcodeexecutor.executor.core;

public class CodeExecutionConfig {

  private String containerImage;

  public CodeExecutionConfig() {
  }

  public CodeExecutionConfig(String containerImage) {
    this.containerImage = containerImage;
  }

  public String getContainerImage() {
    return containerImage;
  }

  public void setContainerImage(String containerImage) {
    this.containerImage = containerImage;
  }
}
