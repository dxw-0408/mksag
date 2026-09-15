from fastapi import APIRouter

from app.services.evaluation_service import evaluation_service

router = APIRouter(prefix="/api/evaluation", tags=["评测"])


@router.get("/summary")
def evaluation_summary():
    return evaluation_service.summary()
