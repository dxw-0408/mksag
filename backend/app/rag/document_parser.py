from pathlib import Path


class DocumentParser:
    """文档解析占位模块：后续接入 PDF / Word / HTML / Markdown 等解析器。"""

    def parse(self, path: str | Path) -> str:
        return Path(path).read_text(encoding="utf-8")
