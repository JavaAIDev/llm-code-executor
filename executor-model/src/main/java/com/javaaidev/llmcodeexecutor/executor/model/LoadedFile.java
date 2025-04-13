
package com.javaaidev.llmcodeexecutor.executor.model;


public class LoadedFile {

    /**
     * MIME type of a loaded file
     * 
     */
    private String mimeType;
    /**
     * Content of a loaded file
     * 
     */
    private String data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoadedFile() {
    }

    /**
     * 
     * @param data
     *     Content of a loaded file.
     * @param mimeType
     *     MIME type of a loaded file.
     */
    public LoadedFile(String mimeType, String data) {
        super();
        this.mimeType = mimeType;
        this.data = data;
    }

    public static LoadedFile.LoadedFileBuilderBase builder() {
        return new LoadedFile.LoadedFileBuilder();
    }

    /**
     * MIME type of a loaded file
     * 
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * MIME type of a loaded file
     * 
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Content of a loaded file
     * 
     */
    public String getData() {
        return data;
    }

    /**
     * Content of a loaded file
     * 
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LoadedFile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("mimeType");
        sb.append('=');
        sb.append(((this.mimeType == null)?"<null>":this.mimeType));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.mimeType == null)? 0 :this.mimeType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LoadedFile) == false) {
            return false;
        }
        LoadedFile rhs = ((LoadedFile) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.mimeType == rhs.mimeType)||((this.mimeType!= null)&&this.mimeType.equals(rhs.mimeType))));
    }

    public static class LoadedFileBuilder
        extends LoadedFile.LoadedFileBuilderBase<LoadedFile>
    {


        public LoadedFileBuilder() {
            super();
        }

        public LoadedFileBuilder(String mimeType, String data) {
            super(mimeType, data);
        }

    }

    public static abstract class LoadedFileBuilderBase<T extends LoadedFile >{

        protected T instance;

        @SuppressWarnings("unchecked")
        public LoadedFileBuilderBase() {
            // Skip initialization when called from subclass
            if (this.getClass().equals(LoadedFile.LoadedFileBuilder.class)) {
                this.instance = ((T) new LoadedFile());
            }
        }

        @SuppressWarnings("unchecked")
        public LoadedFileBuilderBase(String mimeType, String data) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(LoadedFile.LoadedFileBuilder.class)) {
                this.instance = ((T) new LoadedFile(mimeType, data));
            }
        }

        public T build() {
            T result;
            result = this.instance;
            this.instance = null;
            return result;
        }

        public LoadedFile.LoadedFileBuilderBase withMimeType(String mimeType) {
            ((LoadedFile) this.instance).mimeType = mimeType;
            return this;
        }

        public LoadedFile.LoadedFileBuilderBase withData(String data) {
            ((LoadedFile) this.instance).data = data;
            return this;
        }

    }

}
