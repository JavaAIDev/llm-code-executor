
package com.javaaidev.llmcodeexecutor.executor.model;



/**
 * Configuration of collecting output files
 * 
 */
public class OutputFileCollectionConfig {

    /**
     * Should output files be loaded as string
     * 
     */
    private Boolean loadFiles;
    /**
     * Should output files be copied to a directory
     * 
     */
    private Boolean copyFiles;
    /**
     * Directory to copy output files
     * 
     */
    private String copiedFilesPath;
    /**
     * Glob patterns to include files for loading or copying
     * 
     */
    private String includedFilePattern;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OutputFileCollectionConfig() {
    }

    /**
     * 
     * @param includedFilePattern
     *     Glob patterns to include files for loading or copying.
     * @param copyFiles
     *     Should output files be copied to a directory.
     * @param copiedFilesPath
     *     Directory to copy output files.
     * @param loadFiles
     *     Should output files be loaded as string.
     */
    public OutputFileCollectionConfig(Boolean loadFiles, Boolean copyFiles, String copiedFilesPath, String includedFilePattern) {
        super();
        this.loadFiles = loadFiles;
        this.copyFiles = copyFiles;
        this.copiedFilesPath = copiedFilesPath;
        this.includedFilePattern = includedFilePattern;
    }

    public static OutputFileCollectionConfig.OutputFileCollectionConfigBuilderBase builder() {
        return new OutputFileCollectionConfig.OutputFileCollectionConfigBuilder();
    }

    /**
     * Should output files be loaded as string
     * 
     */
    public Boolean getLoadFiles() {
        return loadFiles;
    }

    /**
     * Should output files be loaded as string
     * 
     */
    public void setLoadFiles(Boolean loadFiles) {
        this.loadFiles = loadFiles;
    }

    /**
     * Should output files be copied to a directory
     * 
     */
    public Boolean getCopyFiles() {
        return copyFiles;
    }

    /**
     * Should output files be copied to a directory
     * 
     */
    public void setCopyFiles(Boolean copyFiles) {
        this.copyFiles = copyFiles;
    }

    /**
     * Directory to copy output files
     * 
     */
    public String getCopiedFilesPath() {
        return copiedFilesPath;
    }

    /**
     * Directory to copy output files
     * 
     */
    public void setCopiedFilesPath(String copiedFilesPath) {
        this.copiedFilesPath = copiedFilesPath;
    }

    /**
     * Glob patterns to include files for loading or copying
     * 
     */
    public String getIncludedFilePattern() {
        return includedFilePattern;
    }

    /**
     * Glob patterns to include files for loading or copying
     * 
     */
    public void setIncludedFilePattern(String includedFilePattern) {
        this.includedFilePattern = includedFilePattern;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OutputFileCollectionConfig.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("loadFiles");
        sb.append('=');
        sb.append(((this.loadFiles == null)?"<null>":this.loadFiles));
        sb.append(',');
        sb.append("copyFiles");
        sb.append('=');
        sb.append(((this.copyFiles == null)?"<null>":this.copyFiles));
        sb.append(',');
        sb.append("copiedFilesPath");
        sb.append('=');
        sb.append(((this.copiedFilesPath == null)?"<null>":this.copiedFilesPath));
        sb.append(',');
        sb.append("includedFilePattern");
        sb.append('=');
        sb.append(((this.includedFilePattern == null)?"<null>":this.includedFilePattern));
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
        result = ((result* 31)+((this.copyFiles == null)? 0 :this.copyFiles.hashCode()));
        result = ((result* 31)+((this.includedFilePattern == null)? 0 :this.includedFilePattern.hashCode()));
        result = ((result* 31)+((this.copiedFilesPath == null)? 0 :this.copiedFilesPath.hashCode()));
        result = ((result* 31)+((this.loadFiles == null)? 0 :this.loadFiles.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OutputFileCollectionConfig) == false) {
            return false;
        }
        OutputFileCollectionConfig rhs = ((OutputFileCollectionConfig) other);
        return (((((this.copyFiles == rhs.copyFiles)||((this.copyFiles!= null)&&this.copyFiles.equals(rhs.copyFiles)))&&((this.includedFilePattern == rhs.includedFilePattern)||((this.includedFilePattern!= null)&&this.includedFilePattern.equals(rhs.includedFilePattern))))&&((this.copiedFilesPath == rhs.copiedFilesPath)||((this.copiedFilesPath!= null)&&this.copiedFilesPath.equals(rhs.copiedFilesPath))))&&((this.loadFiles == rhs.loadFiles)||((this.loadFiles!= null)&&this.loadFiles.equals(rhs.loadFiles))));
    }

    public static class OutputFileCollectionConfigBuilder
        extends OutputFileCollectionConfig.OutputFileCollectionConfigBuilderBase<OutputFileCollectionConfig>
    {


        public OutputFileCollectionConfigBuilder() {
            super();
        }

        public OutputFileCollectionConfigBuilder(Boolean loadFiles, Boolean copyFiles, String copiedFilesPath, String includedFilePattern) {
            super(loadFiles, copyFiles, copiedFilesPath, includedFilePattern);
        }

    }

    public static abstract class OutputFileCollectionConfigBuilderBase<T extends OutputFileCollectionConfig >{

        protected T instance;

        @SuppressWarnings("unchecked")
        public OutputFileCollectionConfigBuilderBase() {
            // Skip initialization when called from subclass
            if (this.getClass().equals(OutputFileCollectionConfig.OutputFileCollectionConfigBuilder.class)) {
                this.instance = ((T) new OutputFileCollectionConfig());
            }
        }

        @SuppressWarnings("unchecked")
        public OutputFileCollectionConfigBuilderBase(Boolean loadFiles, Boolean copyFiles, String copiedFilesPath, String includedFilePattern) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(OutputFileCollectionConfig.OutputFileCollectionConfigBuilder.class)) {
                this.instance = ((T) new OutputFileCollectionConfig(loadFiles, copyFiles, copiedFilesPath, includedFilePattern));
            }
        }

        public T build() {
            T result;
            result = this.instance;
            this.instance = null;
            return result;
        }

        public OutputFileCollectionConfig.OutputFileCollectionConfigBuilderBase withLoadFiles(Boolean loadFiles) {
            ((OutputFileCollectionConfig) this.instance).loadFiles = loadFiles;
            return this;
        }

        public OutputFileCollectionConfig.OutputFileCollectionConfigBuilderBase withCopyFiles(Boolean copyFiles) {
            ((OutputFileCollectionConfig) this.instance).copyFiles = copyFiles;
            return this;
        }

        public OutputFileCollectionConfig.OutputFileCollectionConfigBuilderBase withCopiedFilesPath(String copiedFilesPath) {
            ((OutputFileCollectionConfig) this.instance).copiedFilesPath = copiedFilesPath;
            return this;
        }

        public OutputFileCollectionConfig.OutputFileCollectionConfigBuilderBase withIncludedFilePattern(String includedFilePattern) {
            ((OutputFileCollectionConfig) this.instance).includedFilePattern = includedFilePattern;
            return this;
        }

    }

}
