from app.models.faq import FAQCreateRequest, FAQItem


class FAQService:
    def __init__(self) -> None:
        self._items: list[FAQItem] = [
            FAQItem(
                id=1,
                question="图书馆几点关门？",
                answer="脚手架阶段暂未接入真实校方数据，请以学校图书馆官网最新通知为准。",
                category="图书馆",
                source="待接入校方公开资料",
            )
        ]

    def list_items(self) -> list[FAQItem]:
        return self._items

    def create(self, request: FAQCreateRequest) -> FAQItem:
        item = FAQItem(id=len(self._items) + 1, **request.model_dump())
        self._items.append(item)
        return item


faq_service = FAQService()
