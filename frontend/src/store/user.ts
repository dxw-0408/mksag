import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    name: '校园用户',
    loggedIn: false,
  }),

  actions: {
    setUser(name: string) {
      this.name = name
      this.loggedIn = true
    },
  },
})
