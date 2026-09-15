from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    APP_NAME: str = "校园通智能助手"
    APP_VERSION: str = "0.1.0"
    DEBUG: bool = True

    # 大模型配置：正式开发时放入 .env，不要把真实密钥提交到 Git。
    LLM_API_KEY: str = ""
    LLM_BASE_URL: str = ""
    LLM_MODEL: str = ""

    DATABASE_URL: str = "sqlite:///./campus_assistant.db"
    VECTOR_DB_URL: str = ""

    model_config = SettingsConfigDict(env_file=".env", env_file_encoding="utf-8", extra="ignore")


settings = Settings()
