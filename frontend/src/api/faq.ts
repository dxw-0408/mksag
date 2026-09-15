import request from './request'
import type { FAQItem } from '../types/faq'

export async function getFaqList(): Promise<FAQItem[]> {
  const response = await request.get<FAQItem[]>('/faq')
  return response.data
}
