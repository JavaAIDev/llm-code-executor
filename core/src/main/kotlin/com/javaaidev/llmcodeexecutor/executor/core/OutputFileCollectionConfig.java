package com.javaaidev.llmcodeexecutor.executor.core;

public class OutputFileCollectionConfig {

  private Boolean loadFiles;
  private Boolean copyFiles;
  private String copiedFilesPath;
  private String includedFilePattern;

  public OutputFileCollectionConfig() {
  }

  public OutputFileCollectionConfig(Boolean loadFiles, Boolean copyFiles, String copiedFilesPath,
      String includedFilePattern) {
    this.loadFiles = loadFiles;
    this.copyFiles = copyFiles;
    this.copiedFilesPath = copiedFilesPath;
    this.includedFilePattern = includedFilePattern;
  }

  public Boolean getLoadFiles() {
    return loadFiles;
  }

  public void setLoadFiles(Boolean loadFiles) {
    this.loadFiles = loadFiles;
  }

  public Boolean getCopyFiles() {
    return copyFiles;
  }

  public void setCopyFiles(Boolean copyFiles) {
    this.copyFiles = copyFiles;
  }

  public String getCopiedFilesPath() {
    return copiedFilesPath;
  }

  public void setCopiedFilesPath(String copiedFilesPath) {
    this.copiedFilesPath = copiedFilesPath;
  }

  public String getIncludedFilePattern() {
    return includedFilePattern;
  }

  public void setIncludedFilePattern(String includedFilePattern) {
    this.includedFilePattern = includedFilePattern;
  }
}
