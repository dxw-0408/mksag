from collections import defaultdict
from time import monotonic


class SimpleRateLimiter:
    """脚手架阶段的简单限流器；生产环境建议改为 Redis + 分布式限流。"""

    def __init__(self, limit: int = 30, window_seconds: int = 60) -> None:
        self.limit = limit
        self.window_seconds = window_seconds
        self._requests: defaultdict[str, list[float]] = defaultdict(list)

    def allow(self, key: str) -> bool:
        now = monotonic()
        history = [t for t in self._requests[key] if now - t < self.window_seconds]
        self._requests[key] = history
        if len(history) >= self.limit:
            return False
        history.append(now)
        return True


rate_limiter = SimpleRateLimiter()
