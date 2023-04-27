import { userStore } from '@/stores/user'
import { message } from 'ant-design-vue'
import axios from 'axios'

export default (config) => {
  const instance = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 10000
  })

  instance.interceptors.request.use((config) => {
    userStore().isLoading = !userStore().isLoading
    config.headers.Authorization = userStore().accessToken
    return config
  })

  instance.interceptors.response.use(
    (res) => {
      userStore().isLoading = !userStore().isLoading
      if (!res.data.success) {
        message.error(res.data.message)
        return;
      }
      console.log(res.data)
      return res.data.data
    },
    (err) => {
      userStore().isLoading = !userStore().isLoading
      if (err.response.data?.msg) {
        message.error(err.response.data.message)
      } else if (err.response.data?.detail) {
        // 请求参数缺失
        message.error(err.response.data?.detail[0].message)
      } else {
        message.error(err.message)
      }

      return Promise.reject(err)
    }
  )

  return instance(config)
}
