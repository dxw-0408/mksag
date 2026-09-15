class Reranker:
    """Rerank 占位模块：后续接入具体重排序模型。"""

    def rerank(self, query: str, documents: list[dict], top_k: int = 3) -> list[dict]:
        return documents[:top_k]
