from fastapi import APIRouter

from app.models.procedure import ProcedureVO
from app.services.procedure_service import procedure_service

router = APIRouter(prefix="/api/procedures", tags=["办事流程"])


@router.get("", response_model=list[ProcedureVO])
def list_procedures():
    return procedure_service.list_procedures()
