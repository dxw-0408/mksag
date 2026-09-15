<template>
  <div class="page">
    <div class="page-heading">
      <h1>校园智能问答</h1>
      <p>基于校园公开资料进行检索问答，并展示参考来源。</p>
    </div>

    <ChatWindow
      :messages="chatStore.messages"
      :loading="chatStore.loading"
      @submit="ask"
    />
  </div>
</template>

<script setup lang="ts">
import { useChatStore } from '../store/chat'
import { askChat } from '../api/chat'
import ChatWindow from '../components/chat/ChatWindow.vue'

const chatStore = useChatStore()

async function ask(message: string) {
  chatStore.addMessage({
    id: `${Date.now()}-user`,
    role: 'user',
    content: message,
  })

  chatStore.loading = true

  try {
    const result = await askChat({
      message,
      session_id: chatStore.sessionId || undefined,
    })

    if (result.session_id) {
      chatStore.sessionId = result.session_id
    }

    chatStore.addMessage({
      id: `${Date.now()}-assistant`,
      role: 'assistant',
      content: result.answer,
      sources: result.sources,
    })
  } catch {
    chatStore.addMessage({
      id: `${Date.now()}-error`,
      role: 'assistant',
      content: '当前无法连接校园服务，请稍后再试。',
      sources: [],
    })
  } finally {
    chatStore.loading = false
  }
}
</script>
