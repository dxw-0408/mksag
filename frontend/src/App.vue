<template>
  <div class="app-shell">
    <Header />
    <div class="app-body">
      <Sidebar />
      <main class="app-main">
        <RouterView />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import Header from './components/common/Header.vue'
import Sidebar from './components/common/Sidebar.vue'
</script>
