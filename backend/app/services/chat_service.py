from app.models.chat import ChatRequest, ChatResponse
from app.rag.rag_service import rag_service


class ChatService:
    def ask(self, request: ChatRequest) -> ChatResponse:
        return rag_service.answer(request)


chat_service = ChatService()
