<template>
  <section class="chat-window">
    <div class="chat-messages">
      <div v-if="!messages.length" class="empty-state">
        你好，我是校园通。可以向我咨询校历、规章、FAQ和办事流程。
      </div>

      <MessageItem
        v-for="message in messages"
        :key="message.id"
        :message="message"
      />

      <div v-if="loading" class="loading">正在查询校园资料……</div>
    </div>

    <InputBox :disabled="loading" @submit="handleSubmit" />
  </section>
</template>

<script setup lang="ts">
import type { ChatMessage } from '../../types/chat'
import InputBox from './InputBox.vue'
import MessageItem from './MessageItem.vue'

defineProps<{
  messages: ChatMessage[]
  loading: boolean
}>()

const emit = defineEmits<{
  submit: [message: string]
}>()

function handleSubmit(message: string) {
  emit('submit', message)
}
</script>
