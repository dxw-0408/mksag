from pydantic import BaseModel


class ProcedureStep(BaseModel):
    step: int
    title: str
    description: str
    department: str | None = None
    materials: list[str] = []


class ProcedureVO(BaseModel):
    id: int
    name: str
    description: str
    steps: list[ProcedureStep]
