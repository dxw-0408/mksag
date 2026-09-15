from fastapi import APIRouter

from app.models.faq import FAQCreateRequest, FAQItem
from app.services.faq_service import faq_service

router = APIRouter(prefix="/api/faq", tags=["FAQ"])


@router.get("", response_model=list[FAQItem])
def list_faq():
    return faq_service.list_items()


@router.post("", response_model=FAQItem)
def create_faq(body: FAQCreateRequest):
    return faq_service.create(body)
