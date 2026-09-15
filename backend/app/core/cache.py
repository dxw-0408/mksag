from __future__ import annotations

from time import monotonic
from typing import Any


class SimpleTTLCache:
    """脚手架阶段使用的进程内 TTL 缓存；后续可替换为 Redis。"""

    def __init__(self) -> None:
        self._store: dict[str, tuple[float, Any]] = {}

    def get(self, key: str) -> Any | None:
        item = self._store.get(key)
        if item is None:
            return None
        expires_at, value = item
        if monotonic() >= expires_at:
            self._store.pop(key, None)
            return None
        return value

    def set(self, key: str, value: Any, ttl_seconds: int = 300) -> None:
        self._store[key] = (monotonic() + ttl_seconds, value)


cache = SimpleTTLCache()
