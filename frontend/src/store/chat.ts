import { defineStore } from 'pinia'
import type { ChatMessage } from '../types/chat'

export const useChatStore = defineStore('chat', {
  state: () => ({
    sessionId: '' as string,
    messages: [] as ChatMessage[],
    loading: false,
  }),

  actions: {
    addMessage(message: ChatMessage) {
      this.messages.push(message)
    },

    clearMessages() {
      this.messages = []
      this.sessionId = ''
    },
  },
})
