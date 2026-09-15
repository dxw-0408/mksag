from fastapi import FastAPI

from app.api import chat, evaluation, faq, procedure, tools
from app.core.config import settings

app = FastAPI(
    title=settings.APP_NAME,
    version=settings.APP_VERSION,
    description="校园通——基于公开校园资料的智能问答与办事服务后端",
)

app.include_router(chat.router)
app.include_router(faq.router)
app.include_router(procedure.router)
app.include_router(evaluation.router)
app.include_router(tools.router)


@app.get("/", tags=["系统"])
def root():
    return {
        "name": settings.APP_NAME,
        "version": settings.APP_VERSION,
        "message": "校园通 FastAPI 后端已启动",
    }


@app.get("/health", tags=["系统"])
def health_check():
    return {"status": "ok"}
