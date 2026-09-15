import request from './request'

export interface EvaluationSummary {
  total: number
  recall?: number
  faithfulness?: number
}

export async function getEvaluationSummary(): Promise<EvaluationSummary> {
  const response = await request.get<EvaluationSummary>('/evaluation/summary')
  return response.data
}
