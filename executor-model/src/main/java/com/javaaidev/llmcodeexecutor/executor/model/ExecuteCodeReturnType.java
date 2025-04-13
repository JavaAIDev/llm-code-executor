
package com.javaaidev.llmcodeexecutor.executor.model;

import java.util.ArrayList;
import java.util.List;

public class ExecuteCodeReturnType {

    /**
     * Output of code execution
     * 
     */
    private String output;
    /**
     * Error when executing code
     * 
     */
    private String error;
    /**
     * Loaded files
     * 
     */
    private List<LoadedFile> loadedFiles = new ArrayList<LoadedFile>();
    private List<CopiedFile> copiedFiles = new ArrayList<CopiedFile>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ExecuteCodeReturnType() {
    }

    /**
     * 
     * @param output
     *     Output of code execution.
     * @param error
     *     Error when executing code.
     * @param loadedFiles
     *     Loaded files.
     */
    public ExecuteCodeReturnType(String output, String error, List<LoadedFile> loadedFiles, List<CopiedFile> copiedFiles) {
        super();
        this.output = output;
        this.error = error;
        this.loadedFiles = loadedFiles;
        this.copiedFiles = copiedFiles;
    }

    public static ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilderBase builder() {
        return new ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilder();
    }

    /**
     * Output of code execution
     * 
     */
    public String getOutput() {
        return output;
    }

    /**
     * Output of code execution
     * 
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * Error when executing code
     * 
     */
    public String getError() {
        return error;
    }

    /**
     * Error when executing code
     * 
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Loaded files
     * 
     */
    public List<LoadedFile> getLoadedFiles() {
        return loadedFiles;
    }

    /**
     * Loaded files
     * 
     */
    public void setLoadedFiles(List<LoadedFile> loadedFiles) {
        this.loadedFiles = loadedFiles;
    }

    public List<CopiedFile> getCopiedFiles() {
        return copiedFiles;
    }

    public void setCopiedFiles(List<CopiedFile> copiedFiles) {
        this.copiedFiles = copiedFiles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ExecuteCodeReturnType.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("output");
        sb.append('=');
        sb.append(((this.output == null)?"<null>":this.output));
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("loadedFiles");
        sb.append('=');
        sb.append(((this.loadedFiles == null)?"<null>":this.loadedFiles));
        sb.append(',');
        sb.append("copiedFiles");
        sb.append('=');
        sb.append(((this.copiedFiles == null)?"<null>":this.copiedFiles));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.output == null)? 0 :this.output.hashCode()));
        result = ((result* 31)+((this.copiedFiles == null)? 0 :this.copiedFiles.hashCode()));
        result = ((result* 31)+((this.error == null)? 0 :this.error.hashCode()));
        result = ((result* 31)+((this.loadedFiles == null)? 0 :this.loadedFiles.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExecuteCodeReturnType) == false) {
            return false;
        }
        ExecuteCodeReturnType rhs = ((ExecuteCodeReturnType) other);
        return (((((this.output == rhs.output)||((this.output!= null)&&this.output.equals(rhs.output)))&&((this.copiedFiles == rhs.copiedFiles)||((this.copiedFiles!= null)&&this.copiedFiles.equals(rhs.copiedFiles))))&&((this.error == rhs.error)||((this.error!= null)&&this.error.equals(rhs.error))))&&((this.loadedFiles == rhs.loadedFiles)||((this.loadedFiles!= null)&&this.loadedFiles.equals(rhs.loadedFiles))));
    }

    public static class ExecuteCodeReturnTypeBuilder
        extends ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilderBase<ExecuteCodeReturnType>
    {


        public ExecuteCodeReturnTypeBuilder() {
            super();
        }

        public ExecuteCodeReturnTypeBuilder(String output, String error, List<LoadedFile> loadedFiles, List<CopiedFile> copiedFiles) {
            super(output, error, loadedFiles, copiedFiles);
        }

    }

    public static abstract class ExecuteCodeReturnTypeBuilderBase<T extends ExecuteCodeReturnType >{

        protected T instance;

        @SuppressWarnings("unchecked")
        public ExecuteCodeReturnTypeBuilderBase() {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilder.class)) {
                this.instance = ((T) new ExecuteCodeReturnType());
            }
        }

        @SuppressWarnings("unchecked")
        public ExecuteCodeReturnTypeBuilderBase(String output, String error, List<LoadedFile> loadedFiles, List<CopiedFile> copiedFiles) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilder.class)) {
                this.instance = ((T) new ExecuteCodeReturnType(output, error, loadedFiles, copiedFiles));
            }
        }

        public T build() {
            T result;
            result = this.instance;
            this.instance = null;
            return result;
        }

        public ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilderBase withOutput(String output) {
            ((ExecuteCodeReturnType) this.instance).output = output;
            return this;
        }

        public ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilderBase withError(String error) {
            ((ExecuteCodeReturnType) this.instance).error = error;
            return this;
        }

        public ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilderBase withLoadedFiles(List<LoadedFile> loadedFiles) {
            ((ExecuteCodeReturnType) this.instance).loadedFiles = loadedFiles;
            return this;
        }

        public ExecuteCodeReturnType.ExecuteCodeReturnTypeBuilderBase withCopiedFiles(List<CopiedFile> copiedFiles) {
            ((ExecuteCodeReturnType) this.instance).copiedFiles = copiedFiles;
            return this;
        }

    }

}
