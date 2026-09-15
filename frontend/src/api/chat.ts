import request from './request'
import type { ChatRequest, ChatResponse } from '../types/chat'

export async function askChat(payload: ChatRequest): Promise<ChatResponse> {
  const response = await request.post<ChatResponse>('/chat/ask', payload)
  return response.data
}
