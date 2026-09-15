from app.models.procedure import ProcedureStep, ProcedureVO


class ProcedureService:
    def list_procedures(self) -> list[ProcedureVO]:
        return [
            ProcedureVO(
                id=1,
                name="助学贷款办理",
                description="脚手架示例流程，后续接入学校正式办事指南。",
                steps=[
                    ProcedureStep(step=1, title="查看办理条件", description="确认申请资格。", department="学生资助管理部门"),
                    ProcedureStep(step=2, title="准备材料", description="准备学校要求的申请材料。", department="学生资助管理部门"),
                    ProcedureStep(step=3, title="提交申请", description="按学校系统或线下窗口要求提交。", department="学生资助管理部门"),
                ],
            )
        ]


procedure_service = ProcedureService()
