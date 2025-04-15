#!/usr/bin/env just --justfile

build:
    mvn -B -ntp -DskipTests package

[working-directory('container-image-generator')]
generatePythonContainerImages: build
    java -jar target/container-image-generator.jar python \
      profiles/python/base-3.12.json --output=container-images/python/base-3.12

[working-directory('container-image-generator')]
generateJavaContainerImages: build
    java -jar target/container-image-generator.jar java \
          profiles/java/base-21.json --output=container-images/java/base-21

downloadCodeGeneratorCli:
    mvn dependency:copy -Dartifact=com.javaaidev.easyllmtools:code-generator-cli:0.1.9 -DoutputDirectory=target -Dmdep.stripVersion=true

generateCodeExecutorModel: downloadCodeGeneratorCli
    java -jar target/code-generator-cli.jar simple \
      --output=executor-model \
      --model-files-only \
      --parent-group-id=com.javaaidev.llmcodeexecutor \
      --parent-artifact-id=llm-code-executor \
      --parent-artifact-version=0.2.0 \
      --group-id=com.javaaidev.llmcodeexecutor \
      --artifact-id=code-executor-model --artifact-version=0.2.0 \
      --package-name=com.javaaidev.llmcodeexecutor.executor \
      llm-tool-spec/code-executor.json

generateCodeExecutorJava: downloadCodeGeneratorCli
    java -jar target/code-generator-cli.jar simple \
      --output=tools/java \
      --no-model-files \
      --tool-id-prefix=java \
      --parent-group-id=com.javaaidev.llmcodeexecutor \
      --parent-artifact-id=tools \
      --parent-artifact-version=0.2.0 \
      --group-id=com.javaaidev.llmcodeexecutor \
      --artifact-id=tool-java --artifact-version=0.2.0 \
      --model-package-name=com.javaaidev.llmcodeexecutor.executor.model \
      --package-name=com.javaaidev.llmcodeexecutor.executor.java \
      --llm-tool-name="ExecuteJavaCode" \
      --tool-description="Execute Java code with following libraries: guava, commons-lang3, jackson, okhttp. Code is executed in a container. Always write files to the current directory." \
      llm-tool-spec/code-executor.json

generateCodeExecutorPython: downloadCodeGeneratorCli
    java -jar target/code-generator-cli.jar simple \
      --output=tools/python \
      --no-model-files \
      --tool-id-prefix=python \
      --parent-group-id=com.javaaidev.llmcodeexecutor \
      --parent-artifact-id=tools \
      --parent-artifact-version=0.2.0 \
      --group-id=com.javaaidev.llmcodeexecutor \
      --artifact-id=tool-python --artifact-version=0.2.0 \
      --model-package-name=com.javaaidev.llmcodeexecutor.executor.model \
      --package-name=com.javaaidev.llmcodeexecutor.executor.python \
      --llm-tool-name="ExecutePythonCode" \
      --tool-description="Execute Python code with following libraries: numpy, pandas, seaborn, tabulate, sympy. Code is executed in a container. Always write files to the current directory." \
      llm-tool-spec/code-executor.json
