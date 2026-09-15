export interface ProcedureStep {
  id: string | number
  title: string
  description?: string
}

export interface ProcedureItem {
  id: string | number
  name: string
  description?: string
  steps: ProcedureStep[]
}
