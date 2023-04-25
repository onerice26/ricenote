import request from '@/utils/request'

export function getCaptcha() {
  return request({
    url: '/captcha?t=' + new Date().getTime(),
    method: 'get'
  })
}