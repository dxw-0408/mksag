class TextChunker:
    """简单按字符切块的占位实现，正式版本可增加重叠窗口、标题层级等策略。"""

    def split(self, text: str, chunk_size: int = 800) -> list[str]:
        return [text[i : i + chunk_size] for i in range(0, len(text), chunk_size)]
