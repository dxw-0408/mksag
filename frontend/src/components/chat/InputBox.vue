<template>
  <form class="input-box" @submit.prevent="submit">
    <input
      v-model="value"
      type="text"
      placeholder="例如：图书馆几点关门？"
      :disabled="disabled"
    />
    <button type="submit" :disabled="disabled || !value.trim()">发送</button>
  </form>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const props = withDefaults(
  defineProps<{
    disabled?: boolean
  }>(),
  { disabled: false },
)

const emit = defineEmits<{
  submit: [message: string]
}>()

const value = ref('')

function submit() {
  const message = value.value.trim()
  if (!message) return
  emit('submit', message)
  value.value = ''
}
</script>
