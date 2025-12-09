package com.javaaidev.llmcodeexecutor.executor.core;

import java.util.List;

public class ExecuteCodeResult {

  private String output;
  private String error;
  private List<LoadedFile> loadedFiles;
  private List<CopiedFile> copiedFiles;

  public ExecuteCodeResult() {
  }

  public ExecuteCodeResult(String output, String error, List<LoadedFile> loadedFiles,
      List<CopiedFile> copiedFiles) {
    this.output = output;
    this.error = error;
    this.loadedFiles = loadedFiles;
    this.copiedFiles = copiedFiles;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public List<LoadedFile> getLoadedFiles() {
    return loadedFiles;
  }

  public void setLoadedFiles(
      List<LoadedFile> loadedFiles) {
    this.loadedFiles = loadedFiles;
  }

  public List<CopiedFile> getCopiedFiles() {
    return copiedFiles;
  }

  public void setCopiedFiles(
      List<CopiedFile> copiedFiles) {
    this.copiedFiles = copiedFiles;
  }
}
