<template>
  <div v-if="sources.length" class="source-card">
    <div class="source-title">参考来源</div>
    <div v-for="(source, index) in sources" :key="`${source.title}-${index}`" class="source-item">
      <strong>{{ source.title }}</strong>
      <span v-if="source.source"> · {{ source.source }}</span>
      <span v-if="source.page"> · 第 {{ source.page }} 页</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Source } from '../../types/chat'

defineProps<{
  sources: Source[]
}>()
</script>
