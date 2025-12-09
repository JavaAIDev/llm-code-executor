package com.javaaidev.llmcodeexecutor.executor.core

interface CodeExecutor {
    fun execute(
        request: ExecuteCodeParameters
    ): ExecuteCodeResult
}