<template>
  <div :class="['message-item', message.role]">
    <div class="message-role">{{ message.role === 'user' ? '我' : '校园通' }}</div>
    <div class="message-content">{{ message.content }}</div>
    <SourceCard v-if="message.role === 'assistant'" :sources="message.sources || []" />
  </div>
</template>

<script setup lang="ts">
import type { ChatMessage } from '../../types/chat'
import SourceCard from './SourceCard.vue'

defineProps<{
  message: ChatMessage
}>()
</script>
