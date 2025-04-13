
package com.javaaidev.llmcodeexecutor.executor.model;


public class ExecuteCodeConfiguration {

    /**
     * Container image to execute code
     * 
     */
    private String containerImage;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ExecuteCodeConfiguration() {
    }

    /**
     * 
     * @param containerImage
     *     Container image to execute code.
     */
    public ExecuteCodeConfiguration(String containerImage) {
        super();
        this.containerImage = containerImage;
    }

    public static ExecuteCodeConfiguration.ExecuteCodeConfigurationBuilderBase builder() {
        return new ExecuteCodeConfiguration.ExecuteCodeConfigurationBuilder();
    }

    /**
     * Container image to execute code
     * 
     */
    public String getContainerImage() {
        return containerImage;
    }

    /**
     * Container image to execute code
     * 
     */
    public void setContainerImage(String containerImage) {
        this.containerImage = containerImage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ExecuteCodeConfiguration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("containerImage");
        sb.append('=');
        sb.append(((this.containerImage == null)?"<null>":this.containerImage));
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
        result = ((result* 31)+((this.containerImage == null)? 0 :this.containerImage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExecuteCodeConfiguration) == false) {
            return false;
        }
        ExecuteCodeConfiguration rhs = ((ExecuteCodeConfiguration) other);
        return ((this.containerImage == rhs.containerImage)||((this.containerImage!= null)&&this.containerImage.equals(rhs.containerImage)));
    }

    public static class ExecuteCodeConfigurationBuilder
        extends ExecuteCodeConfiguration.ExecuteCodeConfigurationBuilderBase<ExecuteCodeConfiguration>
    {


        public ExecuteCodeConfigurationBuilder() {
            super();
        }

        public ExecuteCodeConfigurationBuilder(String containerImage) {
            super(containerImage);
        }

    }

    public static abstract class ExecuteCodeConfigurationBuilderBase<T extends ExecuteCodeConfiguration >{

        protected T instance;

        @SuppressWarnings("unchecked")
        public ExecuteCodeConfigurationBuilderBase() {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeConfiguration.ExecuteCodeConfigurationBuilder.class)) {
                this.instance = ((T) new ExecuteCodeConfiguration());
            }
        }

        @SuppressWarnings("unchecked")
        public ExecuteCodeConfigurationBuilderBase(String containerImage) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(ExecuteCodeConfiguration.ExecuteCodeConfigurationBuilder.class)) {
                this.instance = ((T) new ExecuteCodeConfiguration(containerImage));
            }
        }

        public T build() {
            T result;
            result = this.instance;
            this.instance = null;
            return result;
        }

        public ExecuteCodeConfiguration.ExecuteCodeConfigurationBuilderBase withContainerImage(String containerImage) {
            ((ExecuteCodeConfiguration) this.instance).containerImage = containerImage;
            return this;
        }

    }

}
