import axios from 'axios'

const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  timeout: 15000,
})

request.interceptors.request.use((config) => {
  // 后续可在这里统一加入 token、request-id 等信息
  return config
})

request.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API request failed:', error)
    return Promise.reject(error)
  },
)

export default request
