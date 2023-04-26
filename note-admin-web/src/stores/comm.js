import { getCaptcha } from '@/service/comm'
import { defineStore } from 'pinia'

export const commStore = defineStore(
  'comm',
  () => {
    
    // 切换角色
    const refreshCaptcha = async () => {
      // 重新拿用户信息
      getCaptcha().then((res) => (dataSource.value = res.data))
    }

    return {
      refreshCaptcha
    }
  },
  {
    persist: true
  }
)
