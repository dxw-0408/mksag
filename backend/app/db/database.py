"""数据库连接占位模块。正式开发阶段可在这里接入 SQLAlchemy / SQLModel。"""

from app.core.config import settings


DATABASE_URL = settings.DATABASE_URL
