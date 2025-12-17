package com.javaaidev.llmcodeexecutor.containerimagegenerator.python

data class PythonProfile(
    val pythonVersion: String,
    val dependencies: List<String>,
) {
    val description: String
        get() = "Python $pythonVersion with libraries: ${dependencies.joinToString(", ")}"
}