from pydantic import BaseModel, Field


class FAQItem(BaseModel):
    id: int
    question: str
    answer: str
    category: str = "其他"
    source: str | None = None


class FAQCreateRequest(BaseModel):
    question: str = Field(..., min_length=1)
    answer: str = Field(..., min_length=1)
    category: str = "其他"
    source: str | None = None
