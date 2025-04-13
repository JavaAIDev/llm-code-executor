
package com.javaaidev.llmcodeexecutor.executor.model;


public class ExecuteCodeParameters {

    /**
     * Code to execute
     * (Required)
     * 
     */
    private String code;
    /**
     * Name of code file
     * 
     */
    private String codeFileName;
    /**
     * Configuration of collecting output files
     * 
     */
    private OutputFileCollectionConfig outputFileCollectionConfig;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ExecuteCodeParameters() {
    }

    /**
     * 
     * @param code
     *     Code to execute.
     * @param codeFileName
     *     Name of code file.
     * @param outputFileCollectionConfig
     *     Configuration of collecting output files.
     */
    public ExecuteCodeParameters(String code, String codeFileName, OutputFileCollectionConfig outputFileCollectionConfig) {
        super();
        this.code = code;
        this.codeFileName = codeFileName;
        this.outputFileCollectionConfig = outputFileCollectionConfig;
    }

    /**
     * 
     * @param code
     *     Code to execute.
     */
    public ExecuteCodeParameters(String code) {
        super();
        this.code = code;
    }

    public static ExecuteCodeParameters.ExecuteCodeParametersBuilderBase builder() {
        return new ExecuteCodeParameters.ExecuteCodeParametersBuilder();
    }

    /**
     * Code to execute
     * (Required)
     * 
     */
    public String getCode() {
        return code;
    }

    /**
     * Code to execute
     * (Required)
     * 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Name of code file
     * 
     */
    public String getCodeFileName() {
        return codeFileName;
    }

    /**
     * Name of code file
     * 
     */
    public void setCodeFileName(String codeFileName) {
        this.codeFileName = codeFileName;
    }

    /**
     * Configuration of collecting output files
     * 
     */
    public OutputFileCollectionConfig getOutputFileCollectionConfig() {
        return outputFileCollectionConfig;
    }

    /**
     * Configuration of collecting output files
     * 
     */
    public void setOutputFileCollectionConfig(OutputFileCollectionConfig outputFileCollectionConfig) {
        this.outputFileCollectionConfig = outputFileCollectionConfig;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ExecuteCodeParameters.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("codeFileName");
        sb.append('=');
        sb.append(((this.codeFileName == null)?"<null>":this.codeFileName));
        sb.append(',');
        sb.append("outputFileCollectionConfig");
        sb.append('=');
        sb.append(((this.outputFileCollectionConfig == null)?"<null>":this.outputFileCollectionConfig));
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
        result = ((result* 31)+((this.codeFileName == null)? 0 :this.codeFileName.hashCode()));
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        result = ((result* 31)+((this.outputFileCollectionConfig == null)? 0 :this.outputFileCollectionConfig.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExecuteCodeParameters) == false) {
            return false;
        }
        ExecuteCodeParameters rhs = ((ExecuteCodeParameters) other);
        return ((((this.codeFileName == rhs.codeFileName)||((this.codeFileName!= null)&&this.codeFileName.equals(rhs.codeFileName)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))))&&((this.outputFileCollectionConfig == rhs.outputFileCollectionConfig)||((this.outputFileCollectionConfig!= null)&&this.outputFileCollectionConfig.equals(rhs.outputFileCollectionConfig))));
    }

    public static class ExecuteCodeParametersBuilder
        extends ExecuteCodeParameters.ExecuteCodeParametersBuilderBase<ExecuteCodeParameters>
    {


        public ExecuteCodeParametersBuilder() {
            super();
        }

        public ExecuteCodeParametersBuilder(String code, String codeFileName, OutputFileCollectionConfig outputFileCollectionConfig) {
            super(code, codeFileName, outputFileCollectionConfig);
        }

        public ExecuteCodeParametersBuilder(String code) {
            super(code);
        }

    }

    public static abstract class ExecuteCodeParametersBuilderBase<T extends ExecuteCodeParameters >{

        protected T instance;

        @SuppressWarnings("unchecked")
        public ExecuteCodeParametersBuilderBase() {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeParameters.ExecuteCodeParametersBuilder.class)) {
                this.instance = ((T) new ExecuteCodeParameters());
            }
        }

        @SuppressWarnings("unchecked")
        public ExecuteCodeParametersBuilderBase(String code, String codeFileName, OutputFileCollectionConfig outputFileCollectionConfig) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeParameters.ExecuteCodeParametersBuilder.class)) {
                this.instance = ((T) new ExecuteCodeParameters(code, codeFileName, outputFileCollectionConfig));
            }
        }

        @SuppressWarnings("unchecked")
        public ExecuteCodeParametersBuilderBase(String code) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeParameters.ExecuteCodeParametersBuilder.class)) {
                this.instance = ((T) new ExecuteCodeParameters(code));
            }
        }

        public T build() {
            T result;
            result = this.instance;
            this.instance = null;
            return result;
        }

        public ExecuteCodeParameters.ExecuteCodeParametersBuilderBase withCode(String code) {
            ((ExecuteCodeParameters) this.instance).code = code;
            return this;
        }

        public ExecuteCodeParameters.ExecuteCodeParametersBuilderBase withCodeFileName(String codeFileName) {
            ((ExecuteCodeParameters) this.instance).codeFileName = codeFileName;
            return this;
        }

        public ExecuteCodeParameters.ExecuteCodeParametersBuilderBase withOutputFileCollectionConfig(OutputFileCollectionConfig outputFileCollectionConfig) {
            ((ExecuteCodeParameters) this.instance).outputFileCollectionConfig = outputFileCollectionConfig;
            return this;
        }

    }

}
