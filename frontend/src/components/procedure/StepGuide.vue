<template>
  <div class="step-guide">
    <div v-for="(step, index) in steps" :key="step.id" class="step-item">
      <div class="step-number">{{ index + 1 }}</div>
      <div>
        <div class="step-title">{{ step.title }}</div>
        <div v-if="step.description" class="step-description">{{ step.description }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { ProcedureStep } from '../../types/procedure'

defineProps<{
  steps: ProcedureStep[]
}>()
</script>
