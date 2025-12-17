package com.javaaidev.llmcodeexecutor.containerimagegenerator.java

data class JavaProfile(val javaVersion: String, val dependencies: List<MavenDependency>) {
    val description: String
        get() = "Java $javaVersion with libraries: ${dependencies.joinToString(", ") { it.coordinate() }}"
}

data class MavenDependency(
    val groupId: String,
    val artifactId: String,
    val artifactVersion: String
)

fun MavenDependency.coordinate() = "$groupId:$artifactId:$artifactVersion"
