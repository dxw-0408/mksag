class EvaluationService:
    def summary(self) -> dict:
        return {
            "dataset_size": 0,
            "retrieval_recall": None,
            "answer_faithfulness": None,
            "status": "评测集尚未导入",
        }


evaluation_service = EvaluationService()
