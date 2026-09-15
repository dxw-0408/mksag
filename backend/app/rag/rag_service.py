from app.models.chat import ChatRequest, ChatResponse
from app.rag.reranker import Reranker
from app.rag.retriever import HybridRetriever


class RAGService:
    def __init__(self) -> None:
        self.retriever = HybridRetriever()
        self.reranker = Reranker()

    def answer(self, request: ChatRequest) -> ChatResponse:
        # 当前是脚手架占位逻辑：没有可靠资料时明确拒答，避免假装“查到了”。
        docs = self.retriever.search(request.question, top_k=5)
        docs = self.reranker.rerank(request.question, docs, top_k=3)

        if not docs:
            return ChatResponse(
                answer="目前还没有接入可验证的校园公开资料，因此无法可靠回答这个问题。",
                sources=[],
                grounded=False,
                session_id=request.session_id,
            )

        return ChatResponse(
            answer="已检索到相关校园资料，正式版本将在这里调用大模型生成带来源的答案。",
            sources=[],
            grounded=True,
            session_id=request.session_id,
        )


rag_service = RAGService()
