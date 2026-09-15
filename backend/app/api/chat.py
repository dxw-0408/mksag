from fastapi import APIRouter, Request
from fastapi.responses import JSONResponse

from app.core.rate_limiter import rate_limiter
from app.models.chat import ChatRequest, ChatResponse
from app.services.chat_service import chat_service

router = APIRouter(prefix="/api/chat", tags=["聊天问答"])


@router.post("/ask", response_model=ChatResponse)
def ask(request: Request, body: ChatRequest):
    client = request.client.host if request.client else "unknown"
    if not rate_limiter.allow(client):
        return JSONResponse(status_code=429, content={"detail": "请求过于频繁，请稍后再试。"})
    return chat_service.ask(body)
