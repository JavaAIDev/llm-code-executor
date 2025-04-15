package com.javaaidev.llmcodeexecutor.executor.python;

import com.javaaidev.easyllmtools.llmtoolspec.Tool;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeConfiguration;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeReturnType;

public abstract class AbstractExecuteCode implements Tool<ExecuteCodeParameters, ExecuteCodeReturnType> {

    protected ExecuteCodeConfiguration config;

    protected AbstractExecuteCode(final ExecuteCodeConfiguration config) {
        this.config = config;
    }

    @Override
    public String getId() {
        return "pythonExecuteCode";
    }

    @Override
    public String getName() {
        return "ExecutePythonCode";
    }

    @Override
    public String getDescription() {
        return "Execute Python code with following libraries: numpy, pandas, seaborn, tabulate, sympy.Code is executed in a container. Always write files to the current directory.";
    }

    @Override
    public String getParametersSchema() {
        return "{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"string\",\"description\":\"Code to execute\"},\"codeFileName\":{\"type\":\"string\",\"description\":\"Name of code file\"},\"outputFileCollectionConfig\":{\"type\":\"object\",\"description\":\"Configuration of collecting output files\",\"properties\":{\"loadFiles\":{\"type\":\"boolean\",\"description\":\"Should output files created in the container be loaded as string\"},\"copyFiles\":{\"type\":\"boolean\",\"description\":\"Should output files created in the container be copied to a directory\"},\"copiedFilesPath\":{\"type\":\"string\",\"description\":\"Target directory of the host machine to copy output files\"},\"includedFilePattern\":{\"type\":\"string\",\"description\":\"Glob patterns to include files for loading or copying\"}}}},\"required\":[\"code\"]}";
    }

    @Override
    public String getReturnTypeSchema() {
        return "{\"type\":\"object\",\"properties\":{\"output\":{\"type\":\"string\",\"description\":\"Output of code execution\"},\"error\":{\"type\":\"string\",\"description\":\"Error when executing code\"},\"loadedFiles\":{\"type\":\"array\",\"description\":\"Loaded files\",\"items\":{\"type\":\"object\",\"properties\":{\"mimeType\":{\"type\":\"string\",\"description\":\"MIME type of a loaded file\"},\"data\":{\"type\":\"string\",\"description\":\"Content of a loaded file\"}}}},\"copiedFiles\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"path\":{\"type\":\"string\",\"description\":\"Path of a copied file in the host machine\"}}}}}}";
    }

    @Override
    public String getExamples() {
        return "{}";
    }
}
