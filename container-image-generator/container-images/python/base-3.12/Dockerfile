FROM ghcr.io/astral-sh/uv:python3.12-bookworm-slim

LABEL "com.javaaidev.llm-code-executor.description"="Python 3.12 with libraries: numpy, pandas, seaborn, tabulate, sympy"

ENV UV_COMPILE_BYTECODE=1

ADD ./app /app

WORKDIR /app
RUN uv sync