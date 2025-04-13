package com.javaaidev.llmcodeexecutor.executor.java;

import com.javaaidev.easyllmtools.llmtoolspec.ConfigurableToolFactory;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeConfiguration;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters;
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeReturnType;

public class ExecuteCodeFactory implements ConfigurableToolFactory<ExecuteCodeParameters, ExecuteCodeReturnType, ExecuteCode, ExecuteCodeConfiguration> {

    @Override
    public ExecuteCode create(final ExecuteCodeConfiguration config) {
        return new ExecuteCode(config);
    }

    @Override
    public String toolId() {
        return "javaExecuteCode";
    }
}
