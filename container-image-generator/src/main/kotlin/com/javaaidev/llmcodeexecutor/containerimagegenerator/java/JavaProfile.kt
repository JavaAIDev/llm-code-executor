package com.javaaidev.llmcodeexecutor.containerimagegenerator.java

data class JavaProfile(val javaVersion: String, val dependencies: List<MavenDependency>)

data class MavenDependency(
    val groupId: String,
    val artifactId: String,
    val artifactVersion: String
)
