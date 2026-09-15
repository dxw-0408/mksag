export interface Source {
  title: string
  source?: string
  page?: number
  url?: string
}

export interface ChatRequest {
  message: string
  session_id?: string
}

export interface ChatMessage {
  id: string
  role: 'user' | 'assistant'
  content: string
  sources?: Source[]
}

export interface ChatResponse {
  answer: string
  sources: Source[]
  session_id?: string
}
