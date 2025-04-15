
package com.javaaidev.llmcodeexecutor.executor.model;


public class CopiedFile {

    /**
     * Path of a copied file in the host machine
     * 
     */
    private String path;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CopiedFile() {
    }

    /**
     * 
     * @param path
     *     Path of a copied file in the host machine.
     */
    public CopiedFile(String path) {
        super();
        this.path = path;
    }

    public static CopiedFile.CopiedFileBuilderBase builder() {
        return new CopiedFile.CopiedFileBuilder();
    }

    /**
     * Path of a copied file in the host machine
     * 
     */
    public String getPath() {
        return path;
    }

    /**
     * Path of a copied file in the host machine
     * 
     */
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CopiedFile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("path");
        sb.append('=');
        sb.append(((this.path == null)?"<null>":this.path));
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
        result = ((result* 31)+((this.path == null)? 0 :this.path.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CopiedFile) == false) {
            return false;
        }
        CopiedFile rhs = ((CopiedFile) other);
        return ((this.path == rhs.path)||((this.path!= null)&&this.path.equals(rhs.path)));
    }

    public static class CopiedFileBuilder
        extends CopiedFile.CopiedFileBuilderBase<CopiedFile>
    {


        public CopiedFileBuilder() {
            super();
        }

        public CopiedFileBuilder(String path) {
            super(path);
        }

    }

    public static abstract class CopiedFileBuilderBase<T extends CopiedFile >{

        protected T instance;

        @SuppressWarnings("unchecked")
        public CopiedFileBuilderBase() {
            // Skip initialization when called from subclass
            if (this.getClass().equals(CopiedFile.CopiedFileBuilder.class)) {
                this.instance = ((T) new CopiedFile());
            }
        }

        @SuppressWarnings("unchecked")
        public CopiedFileBuilderBase(String path) {
            // Skip initialization when called from subclass
            if (this.getClass().equals(CopiedFile.CopiedFileBuilder.class)) {
                this.instance = ((T) new CopiedFile(path));
            }
        }

        public T build() {
            T result;
            result = this.instance;
            this.instance = null;
            return result;
        }

        public CopiedFile.CopiedFileBuilderBase withPath(String path) {
            ((CopiedFile) this.instance).path = path;
            return this;
        }

    }

}
