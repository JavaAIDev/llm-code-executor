package com.javaaidev.llmcodeexecutor.executor.core;

public class LoadedFile {

  private String mimeType;
  private String data;

  public LoadedFile() {
  }

  public LoadedFile(String mimeType, String data) {
    this.mimeType = mimeType;
    this.data = data;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
