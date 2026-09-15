import request from './request'
import type { ProcedureItem } from '../types/procedure'

export async function getProcedureList(): Promise<ProcedureItem[]> {
  const response = await request.get<ProcedureItem[]>('/procedures')
  return response.data
}
