export function formatTime(date = new Date()): string {
  return date.toLocaleString('zh-CN', {
    hour12: false,
  })
}
