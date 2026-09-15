<template>
  <div class="page">
    <div class="page-heading">
      <h1>办事流程</h1>
      <p>将校园事务拆解为清晰的办理步骤。</p>
    </div>

    <div class="card-grid">
      <section v-for="item in procedures" :key="item.id" class="card">
        <h2>{{ item.name }}</h2>
        <p>{{ item.description || '校园事务办理流程' }}</p>
        <StepGuide :steps="item.steps" />
      </section>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getProcedureList } from '../api/procedure'
import type { ProcedureItem } from '../types/procedure'
import StepGuide from '../components/procedure/StepGuide.vue'

const procedures = ref<ProcedureItem[]>([
  {
    id: 'demo',
    name: '助学贷款申请',
    description: '用于展示脚手架中的办事流程组件。',
    steps: [
      { id: 1, title: '准备材料', description: '准备申请所需材料。' },
      { id: 2, title: '提交申请', description: '按学校要求提交材料。' },
      { id: 3, title: '审核', description: '等待相关部门审核。' },
    ],
  },
])

onMounted(async () => {
  try {
    const data = await getProcedureList()
    if (data.length) procedures.value = data
  } catch {
    // 保留演示数据，便于脚手架单独运行
  }
})
</script>
