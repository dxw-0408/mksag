<template>
  <div class="page">
    <div class="page-heading">
      <h1>校园工具</h1>
      <p>预留课表、图书馆开放时间等工具调用入口。</p>
    </div>

    <section class="card">
      <h2>图书馆开放时间</h2>
      <p v-if="loading">正在查询……</p>
      <p v-else-if="hours">开放时间：{{ hours.open }} - {{ hours.close }}</p>
      <p v-else>尚未获取数据，后续可连接 FastAPI 工具接口。</p>
    </section>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getLibraryHours, type LibraryHours } from '../api/tools'

const hours = ref<LibraryHours | null>(null)
const loading = ref(false)

onMounted(async () => {
  loading.value = true
  try {
    hours.value = await getLibraryHours()
  } catch {
    // 后续接入真实工具接口
  } finally {
    loading.value = false
  }
})
</script>
