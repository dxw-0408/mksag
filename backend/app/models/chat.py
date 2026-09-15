from pydantic import BaseModel, Field


class Source(BaseModel):
    title: str
    url: str | None = None
    page: int | None = None
    snippet: str | None = None


class ChatRequest(BaseModel):
    question: str = Field(..., min_length=1, description="用户问题")
    session_id: str | None = None


class ChatResponse(BaseModel):
    answer: str
    sources: list[Source] = []
    grounded: bool = False
    session_id: str | None = None
